-- 代理商活动2017-05-13~2017-05-20活动结算
drop procedure IF EXISTS updateTaxesInviteWeek_send;
delimiter //
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateTaxesInviteWeek_send`()
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
					select  IFNULL(p.paytotal1,0) ,IFNULL(p.invitetotal1,0),IFNULL(p.proceeds1,0),
					CONCAT('[',
												GROUP_CONCAT(
														 CONCAT('[',ifNULL(p.paytotal1,'0')),',',
														 CONCAT(ifNULL(p.proceeds1,'0'),','),
														 CONCAT(ifNULL(p.invitetotal1,'0')),']'						 
					)
					,']') info 
					from ( 
					SELECT a.paytotal1 paytotal1,a.invitetotal1 invitetotal1,
				 (case  when a.paytotal1>=180 and a.paytotal1<300  then 20 
		                when  a.paytotal1>=300 and  a.paytotal1<500 then 35 
						when  a.paytotal1>=500 and  a.paytotal1<800 then 60 
						when  a.paytotal1>=800 and  a.paytotal1<1000 then 100
						when  a.paytotal1>=1000 and  a.paytotal1<1500 then 130
						when  a.paytotal1>=1500 and  a.paytotal1<2000 then 240
		                when  a.paytotal1>=2000 then 400 end) proceeds1 
					 from (
					SELECT SUM(paytotal) AS paytotal1,SUM(invitetotal) AS invitetotal1 FROM taxes_invite WHERE date>='2017-05-13' AND date<='2017-05-20' AND mid=@mid) a 
 ) p;

	
   DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;
     set nowdate = DATE_ADD(nowdate,INTERVAL -1 week);  
		 set endtmp = DATE_ADD(endtmp,INTERVAL -1 day);  

	
	open cur;
	
    grade_loop: LOOP 
		
		FETCH cur INTO tmid, tparent_id,tscale,mktime,tbelongid;
			IF done=1 THEN
				 LEAVE grade_loop;
			END IF;

      set @mid=tmid;

      
					open payCount;
						class_loop: LOOP
					FETCH payCount INTO total1 ,invitotal1, ceeds1, allInfo;
						 IF done=1 THEN
							 LEAVE class_loop;
						END IF;
														set counttotal=total1;    
														set countinvitotal=invitotal1; 
														set countceeds=ceeds1;   
							
							
									 if counttotal > 0 or countinvitotal > 0  then 
														
											 
													 INSERT IGNORE INTO taxes_invite_week_send SET mid=@mid,parentid=tparent_id,paytotal=counttotal,rebatetotal=countceeds,taxrate=0,
													 bindpeople=countinvitotal,date='2017-05-20',info=allInfo;

										 end if ;
						END LOOP class_loop;
					CLOSE payCount;
					

    SET done=0;
END LOOP grade_loop;
 	  
	CLOSE cur;
END
//
delimiter ;
