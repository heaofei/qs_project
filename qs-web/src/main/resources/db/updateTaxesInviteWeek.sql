	drop procedure IF EXISTS updateTaxesInviteWeek;
	delimiter //
	CREATE DEFINER=`root`@`localhost` PROCEDURE `updateTaxesInviteWeek`()
	BEGIN


	DECLARE done INT DEFAULT 0;



	DECLARE tmid INT(11) DEFAULT 0;
	DECLARE tparent_id int(11) DEFAULT 0;
	DECLARE tscale TINYINT(3) DEFAULT NULL;
	DECLARE mktime int(11) DEFAULT 0;
	DECLARE tbelongid int(11) DEFAULT 0;

	DECLARE nowdate date DEFAULT NOW();
	DECLARE endtmp date DEFAULT NOW();

	DECLARE total1 double(11,2) DEFAULT 0;
	DECLARE invitotal1 double(11,2) DEFAULT 0;
	DECLARE total2 double(11,2) DEFAULT 0;
	DECLARE invitotal2 double(11,2) DEFAULT 0;
	DECLARE total3 double(11,2) DEFAULT 0;
	DECLARE invitotal3 double(11,2) DEFAULT 0;
	DECLARE ceeds1 double(11,2) DEFAULT 0;
	DECLARE ceeds2 double(11,2) DEFAULT 0;
	DECLARE ceeds3 double(11,2) DEFAULT 0;

	DECLARE counttotal double(11,2) DEFAULT 0;
	DECLARE countinvitotal double(11,2) DEFAULT 0;
	DECLARE countceeds double(11,2) DEFAULT 0;
	declare allInfo VARCHAR(200) DEFAULT null;

	DECLARE cur CURSOR FOR
	SELECT mid,parent_id,scale,mktime,belongid FROM gd_majiang.memberagents WHERE status=0;



	DECLARE payCount CURSOR FOR
	select  IFNULL(p.paytotal1,0) ,IFNULL(p.invitetotal1,0),IFNULL(p.proceeds1,0),IFNULL(p.paytotal2,0),IFNULL(p.invitetotal2,0),IFNULL(p.proceeds2,0),IFNULL(p.paytotal3,0),IFNULL(p.invitetotal3,0),IFNULL(p.proceeds3,0),
	CONCAT('[',
	GROUP_CONCAT(
	CONCAT('[',ifNULL(p.paytotal1,'0')),',',
	CONCAT(ifNULL(p.proceeds1,'0'),','),
	CONCAT(ifNULL(p.invitetotal1,'0')),',],',
	CONCAT('[',ifNULL(p.paytotal2,'0')),',',
	CONCAT(ifNULL(p.proceeds2,'0'),','),
	CONCAT(ifNULL(p.invitetotal2,'0')),',],',
	CONCAT('[',ifNULL(p.paytotal3,'0')),',',
	CONCAT(ifNULL(p.proceeds3,'0'),','),
	CONCAT(ifNULL(p.invitetotal3,'0')),',]'
	)
	,']') info
	from (
	SELECT a.paytotal1 paytotal1,a.invitetotal1 invitetotal1,b.paytotal2 paytotal2,b.invitetotal2 invitetotal2,c.paytotal3 paytotal3,c.invitetotal3 invitetotal3,
	(case  when a.paytotal1<=3500 then a.paytotal1*0.4 when  a.paytotal1>3500 and  a.paytotal1<7000 then a.paytotal1*0.4 when  a.paytotal1>=7000 then a.paytotal1*0.4 end) proceeds1 ,
	(case  when b.paytotal2>0 then b.paytotal2*0.08 else 0 end) proceeds2 ,
	(case  when c.paytotal3>0 then c.paytotal3*0.05 else 0 end) proceeds3
	from (
	SELECT SUM(paytotal) AS paytotal1,SUM(invitetotal) AS invitetotal1 FROM taxes_invite WHERE date>=nowdate AND date<=endtmp AND mid=@mid ) a,
	(SELECT SUM(paytotal) AS paytotal2,SUM(invitetotal) AS invitetotal2 FROM taxes_invite WHERE date>=nowdate AND date<=endtmp AND parentid=@mid) b,
	(SELECT SUM(i.paytotal) AS paytotal3,SUM(i.invitetotal) AS invitetotal3 FROM taxes_invite i INNER JOIN  gd_majiang.memberagents a ON a.parent_id=@mid AND a.status=0 AND i.parentid=a.mid WHERE
	i.date>=nowdate AND i.date<=endtmp ) c ) p;


	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;
	set nowdate = DATE_ADD(nowdate,INTERVAL -1 week);
	set endtmp = DATE_ADD(endtmp,INTERVAL 1 day);


	open cur;

	grade_loop: LOOP

	FETCH cur INTO tmid, tparent_id,tscale,mktime,tbelongid;
	IF done=1 THEN
	LEAVE grade_loop;
	END IF;

	set @mid=tmid;


	open payCount;
	class_loop: LOOP
	FETCH payCount INTO total1 ,invitotal1, ceeds1, total2, invitotal2, ceeds2,total3, invitotal3, ceeds3,allInfo;
	IF done=1 THEN
	LEAVE class_loop;
	END IF;
	set counttotal=total1+total2+total3;
	set countinvitotal=invitotal1+invitotal2+invitotal3;
	
	set @countSumInvite=(select SUM(bindpeople) from taxes_invite_week where mid=@mid); -- 统计总过去总邀请人数
  	set @cSUM=@countSumInvite+countinvitotal; -- 当前用户总邀请人数用于判断2 3 团队充值返利是否加入

  	if @cSUM >=15 THEN
		set countceeds=ceeds1+ceeds2+ceeds3;
  	ELSE
    	set countceeds=ceeds1;
	end if;


	if  counttotal > 0 or countinvitotal > 0  then


	INSERT IGNORE INTO taxes_invite_week SET mid=@mid,parentid=tparent_id,paytotal=counttotal,rebatetotal=countceeds,taxrate=0,
	bindpeople=countinvitotal,date=DATE_ADD(NOW(),INTERVAL -1 day),info=allInfo;
	-- 商务周表统计信息处理   注意*：游戏类型固定'2017-05-14'
	INSERT IGNORE INTO gd_majiang_pub.biz_invite_week SET bizid=tbelongid,date=DATE_ADD(NOW(),INTERVAL -1 day),paytotal=counttotal,gametype=6,rebatetotal=countceeds,bindpeople=countinvitotal
	ON DUPLICATE KEY UPDATE paytotal=paytotal+total1,rebatetotal=rebatetotal+countceeds,bindpeople=bindpeople+countinvitotal;
	end if ;
	END LOOP class_loop;
	CLOSE payCount;


	SET done=0;
	END LOOP grade_loop;

	CLOSE cur;
	END
	//
	delimiter ;