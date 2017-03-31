<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<div class="page-content">
    <div class="controls controls-row">
        <div class="form-horizontal" role="form">
            <div class="form-group">
<!--                 <label class="control-label col-sm-1 no-padding-right">年份</label> -->
				<div class="col-sm-3">
					<div class="clearfix">
						<select class="form-control" name="seacrhVersionKeySite"
							id="searchYear">
							<option selected="selected">请选择年份</option>
							<c:forEach items="${years}" var="y">
								<option value="${y}">${y}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				
				<div class="col-sm-3">
					<div class="input-group" style="width: 100%;">
						<select class="form-control" name="seacrhVersionKeySite"
							id="searchDate">
							<option selected="selected">请选择日期</option>
						</select> 
						<span class="input-group-btn">
							<button id="btnSearch" class="btn btn-primary btn-sm"
								type="button">
								<i class="fa fa-search"></i>查询
							</button>
						</span>
					</div>
				</div>
				
			</div>
        </div>
    </div>

    <div class="row" style="margin-top:5px;">
        <div class="col-xs-12 widget-container-col ui-sortable"
             style="min-height: 127px;">
            <!-- #section:custom/widget-box.options.transparent -->
            <div class="widget-box transparent ui-sortable-handle"
                 style="opacity: 1; z-index: 0;">
                <div class="widget-header">
                    <h4 class="widget-title lighter">代理商周信息统计</h4>
                    <div class="widget-toolbar no-border">
                        <a href="#" data-action="fullscreen" class="orange2">
                            <i class="ace-icon fa fa-arrows-alt"></i>
                        </a>
                        <a href="#" data-action="collapse" class="green">
                            <i class="ace-icon fa fa-chevron-up"></i>
                        </a>
                    </div>
                </div>
					<table class="table table-condensed table-hover">
			                <thead>
			                <tr>
			                    <th></th>
			                    <th>充值<br>总额</th>
			                    <th>结算<br>总额</th>
			                    <th>操作</th>
			                </tr>
			                </thead>
			                <tbody>
			                <tr>
			                    <td>直属会员</td>
			                    <td class="teamInfo00">${data.teamInfo00}</td>
			                    <td class="teamInfo01">${data.teamInfo01}</td>
			                    <td><button class="btn btn-default table-btn" onclick="showSettleDetail(1)">明细</button></td>
			                </tr>
			                <tr>
			                    <td>二级团队</td>
			                    <td class="teamInfo10">${data.teamInfo10}</td>
			                    <td class="teamInfo11">${data.teamInfo11}</td>
			                    <td><button class="btn btn-default table-btn" onclick="showSettleDetail(2)">明细</button></td>
			                </tr>
			                <tr>
			                    <td>三级团队</td>
			                    <td class="teamInfo20">${data.teamInfo20}</td>
			                    <td class="teamInfo21">${data.teamInfo21}</td>
			                    <td><button class="btn btn-default table-btn" onclick="showSettleDetail(3)">明细</button></td>
			                </tr>
			                <tr>
			                    <td>总计</td>
			                    <td class="paytotal">${data.paytotal}</td>
			                    <td class="rebateTotal">${data.rebateTotal}</td>
			                    <td>----</td>
			                </tr>
			                <tr>
			                    <td>实际发放</td>
			                    <td>----</td>
			                    <td>${data.result.rebatetotal }</td>
			                    <td>
			                    <c:if test="${data.result.isaward ==0 }">
			                    
			                        <span class="text-success">已发放</span>
			                    </c:if>
			                    <c:if test="${data.result.isaward ==1 }">
			                        <span class="text-danger">未发放</span>
			                    </c:if>
			                    </td>
			                </tr>
			                </tbody>
            </table>
                 <div class="well">
			            提示：<br>
			            1、直属会员：3500元以下，分成比例 ${rouleParam.lowScale} %， 达到3500元以上7000元以下，分成比例 ${rouleParam.middleScale}%， 
			            7000元以上，分成比例  ${rouleParam.highScale}%； 二级团队分成比例${rouleParam.team1}%，三级团队分成比例 ${rouleParam.team2}%。<br>
			            2、您在成为代理商的前三个月，暂不收税，超过三个月之后按4%收税。<br>
			            3、每周一和周二统计，周三至周五发放提成，提成满足50元以上的发放，未满50元累计到下周发放。
			     </div>
            </div>
        </div>
    </div>
</div>

<script>
    var json = '${jsonDate}';
    var obj = JSON.parse(json);
    $(function () {
        $('#searchYear').on('change',function (e) {
            var yearVal = $(this).children('option:selected').val();
            $('#searchDate').html('<option selected="selected">请选择日期</option>');
            if (yearVal == '请选择年份') {
                $('#searchDate').html('<option selected="selected">请选择日期</option>');
                return;
            }
            //alert(yearVal);
            var date = handleInitResponse.execute(yearVal);
            for (var i = 0;i<date.length;i++) {
                var arr = date[i];
                var v = arr.replace('月','-').replace('月','-');
                var vv = v.replace('日','-').replace('日','').substr(8);
                $('#searchDate').append('<option value="'+vv+'">'+arr+'</option>');
            }
        });

    });

    var handleInitResponse = {
        execute:function (data) {
            return eval('obj.a' + data );
        }
    }
    
    $("#btnSearch").click(customSearch);
    
    //查询
    function customSearch() {
        var searchYear =  $('#searchYear').val();
        var searchDate =  $('#searchDate').val();
        if (searchYear == "请选择年份" || searchDate == "请选择日期") {
            layer.msg('请选择查询时间',{time:800});
            return;
        }
        var requirtDate = searchYear + "-" + searchDate;
       
    	var url = "${ctx}/agentroom/teamWeekSttlementSubmit.html";
        $.ajax({
			type : "POST",
			url : url,
			data : {"time":requirtDate},
			dataType : "json",
			success : function(msg) {
				if (msg.success == true) {
					 $(".teamInfo00").text(msg.data.teamInfo00);
					 $(".teamInfo01").text(msg.data.teamInfo01);
				     $(".teamInfo10").text(msg.data.teamInfo10);
					 $(".teamInfo11").text(msg.data.teamInfo11);
					 $(".teamInfo20").text(msg.data.teamInfo20);
				     $(".teamInfo21").text(msg.data.teamInfo21);
					 $(".paytotal").text(msg.data.paytotal);
					 $(".rebateTotal").text(msg.data.rebateTotal);
				} else {
					layer.msg(msg.message, {
						icon : 5,
						time : 500
					});
				}
			}

		});
    }
    
    
   //查看明细
   
   	function showSettleDetail(type){
   		var searchYear =  $('#searchYear').val();
        var searchDate =  $('#searchDate').val();
        var requirtDate="";
        if (searchYear != "请选择年份" || searchDate != "请选择日期") {
            requirtDate= searchYear + "-" + searchDate;
        }
      
		var title= $('#searchDate :selected').text() ;
		if (type==1){
			title=title+"(直属会员)"
		}
		if (type==2){
			title=title+"(二级团队)"
		}
		if (type==3){
			title=title+"(三级团队)"
		}
		  layer.open({
		        type: 2,
		        title:title,
		        area: ['95%','90%'],
		        fixed: false, //不固定
		        maxmin: true,
		        content: sys.rootPath + '/agentroom/submitTaxesInviteUi.html?endTime='+requirtDate+"&type="+type
		});
	}
</script>