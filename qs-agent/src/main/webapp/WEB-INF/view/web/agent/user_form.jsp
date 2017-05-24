<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<script src="${ctx}/resources/js/customer/index/index_list.js" type="text/javascript"></script>
<%-- <%@include file="/common/common.jspf" %> --%>
<style>
body {
	background: #FFF;
}
.help-block{color: red;}
</style>
<div class="page-content">

    <div class="row" style="margin-top:5px;">
	     <form id="storeForm" name="storeForm"  class="form-horizontal" role="form" method="post" >
	     <div class="col-xs-12" style="background-color: #f5f5f5;font-size: 10px;color: red; margin-left: 10px; height: 20px; ">
	                   <span>提示：个人资料关系到您的返利提成发放，请务必仔细填写。</span>
			</div>
	     	<label class="col-xs-12">用户名</label>
	        <div class="col-xs-12">
                    <div class="clearfix">
                        <input class="form-control" name="realname" id="realname" type="text"
                               value="${agents.realname }" placeholder="您的真实姓名"/>
                    </div>
			</div>
			<label class="col-xs-12">用户手机号</label>
			<div class="col-xs-12 " >
			<div class="clearfix">
                        <input class="form-control" name="phone" id="phone" type="text"
                               value="${agents.phone }" placeholder="您的手机号码"/>
                    </div>
			</div>
			<label class="col-xs-12">银行卡号</label>
			<div class="col-xs-12" >
			  <div class="clearfix">
                        <input class="form-control" name="bankcard" id="bankcard" type="text"
                               value="${agents.bankcard }" placeholder="您的银行卡号"/>
                    </div>
			</div>
			<label class="col-xs-12">开户银行</label>
			<div class="col-xs-12" >
			<div class="clearfix">
                        <select class="form-control" id="bank" name="bank" style="width: 100%">
                            <option value="中国邮政储蓄银行" <c:if test="${agents.bank == '中国邮政储蓄银行'}"> selected='selected'</c:if>>中国邮政储蓄银行</option>
                                <option value="中国工商银行" <c:if test="${agents.bank == '中国工商银行'}"> selected='selected'</c:if>>中国工商银行</option>
                                <option value="中国建设银行" <c:if test="${agents.bank == '中国建设银行'}"> selected='selected'</c:if>>中国建设银行</option>
                                <option value="中国农业银行" <c:if test="${agents.bank == '中国农业银行'}"> selected='selected'</c:if>>中国农业银行</option>                
                                <option value="中国民生银行" <c:if test="${agents.bank == '中国民生银行'}"> selected='selected'</c:if>>中国民生银行</option>
                                <option value="中国光大银行" <c:if test="${agents.bank == '中国光大银行'}"> selected='selected'</c:if>>中国光大银行</option>
                                <option value="上海浦东发展银行" <c:if test="${agents.bank == '上海浦东发展银行'}"> selected='selected'</c:if>>上海浦东发展银行</option>
                                <option value="广东发展银行" <c:if test="${agents.bank == '广东发展银行'}"> selected='selected'</c:if>>广东发展银行</option>                
                                <option value="招商银行" <c:if test="${agents.bank == '招商银行'}"> selected='selected'</c:if>>招商银行</option>
                                <option value="中国银行" <c:if test="${agents.bank == '中国银行'}"> selected='selected'</c:if>>中国银行</option>
                                <option value="交通银行" <c:if test="${agents.bank == '交通银行'}"> selected='selected'</c:if>>交通银行</option>
                                <option value="华夏银行" <c:if test="${agents.bank == '华夏银行'}"> selected='selected'</c:if>>华夏银行</option>
                                <option value="平安银行" <c:if test="${agents.bank == '平安银行'}"> selected='selected'</c:if>>平安银行</option>
                                <option value="兴业银行" <c:if test="${agents.bank == '兴业银行'}"> selected='selected'</c:if>>兴业银行</option>
                                <option value="上海银行" <c:if test="${agents.bank == '上海银行'}"> selected='selected'</c:if>>上海银行</option>
                                <option value="中信银行" <c:if test="${agents.bank == '中信银行'}"> selected='selected'</c:if>>中信银行</option>
                        </select>
                    </div>
			</div>
			
			<div class="col-xs-12" style="margin-top: 20px">
				<div class="clearfix">
					<div class="center">
     					<button id="btnAdd" type="button" onclick="javascript:$('#storeForm').submit();" class="btn btn-primary btn-sm">
					          保存
					    </button>
							<button class="btn btn-success btn-sm" type="button"
								onclick="closeLayer()">
								<i class="icon-trash bigger-200"></i>返回
							</button>
					</div>
             </div>
			</div>
	     </form>
    </div>
</div>
<script>
window.onresize=function(){  
	$("#head_title").width($("#realname").width()); 
}  
$("#head_title").width($("#realname").width());
            function closeLayer() {
            	webside.index.initHomePage();
            }
 
//  地区
$("#province").change(function(){
	   loadCity($("#province").val());  
});

$("#city").change(function(){
	   loadcounty($("#city").val());  
});
function loadCity(parentId) {
	
	if (parentId != -1) {
			$.ajax({
				url : 'area.html?level=1&code=' + parentId,
				type : 'POST',
				dataType : 'JSON',
				timeout : 5000,
				error : function() {
					layer.msg("数据加载失败", {
						icon : 3
					});
				},
				success : function(msg) {
					$("#city").empty();
					$.each(eval(msg), function(i, item) {
						
						$(
								"<option value='"+item.code+"'>" + item.aname
										+ "</option>").appendTo("#city");
					});
				}
			});
		} else {
			return;
		}

	};

	function loadcounty(parentId) {
	if (parentId != -1) {
			$.ajax({
				url : 'area.html?level=2&code=' + parentId,
				type : 'POST',
				dataType : 'JSON',
				timeout : 5000,
				error : function() {
					layer.msg("数据加载失败", {
						icon : 3
					});
				},
				success : function(msg) {
					$("#county").empty();
					$.each(eval(msg), function(i, item) {
						$(
								"<option value='"+item.code+"'>" + item.aname
										+ "</option>").appendTo("#county");
					});
				}
			});
		} else {
			return;
		}

	};

	var validateForm= $(function() {
		$('#storeForm').validate(
				{
					errorElement : 'div',
					errorClass : 'help-block',
					focusInvalid : false,
					ignore : "",
					rules : {
						realname : {
							required : true
						},
						phone : {
							required : true
						},
						bankcard : {
							required : true
						},
						bank : {
							required : true
						}
						
					},
					messages : {
						realname : "请填写用户名",
						phone : "请填写手机号",
						bankcard : "请输入银行卡号",
						bank : "请选择银行名"
					},
					highlight : function(e) {
						$(e).closest('.form-group').removeClass('has-info')
								.addClass('has-error');
					},
					success : function(e) {
						$(e).closest('.form-group').removeClass('has-error')
								.addClass('has-success');
						$(e).remove();
					},
					errorPlacement : function(error, element) {
						error.insertAfter(element.parent());
					},
					 submitHandler: function (form) {
						 submits();
		            } 
				});

	});
	
	//提交表单
	 var submits=function (){
		 
		 var url="${ctx}/user/updateUserInfo.html";
		 /* var name=$("#name").val();
		 var phone=$("#phone").val();
		 var card=$("#card").val();
		 var bankName=$("#bankName option:selected").val();
		 var province_id=$("#province_id").val();
		 var city_id=$("#city_id").val();
		 var city_id_text=$("#city_id option:selected").text();
		 var county_id=$("#county_id").val();
		 
		 {
	         "name":name, 
	         "phone":phone,
	         "card":card,
	         "bankName":bankName,
	         "province_id":province_id,
	         "city_id":city_id,
	         "city_id_text":city_id_text,
	         "county_id":county_id
	    }
		 */
		 var memberAgents =$("#storeForm").serialize();   
		 $.ajax({
	         type: "POST",
	         url: url,
	         data:memberAgents,
	         dataType: "json",
	         success: function (msg) {
	        	 if (msg.success==true){
	        		  layer.msg(msg.message, {icon: 6,time:500});
	        		  $(".page-content").load(sys.rootPath + "/home.html");
	        	 }else {
	        		 layer.msg(msg.message, {icon: 5,time:500});
	        	 }
	         }
	         
	     }); 
	 }
</script>