<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%@include file="/common/common.jspf" %>
<style>
body {
	background: #FFF;
}
</style>
<div class="page-content">
            <form action="" name="bind_phone_form" role="form" id="storeForm" autocomplete="off">
                <div class="form-group col-xs-12">
                    <div class="row">
                        <label class="col-xs-3 col-sm-2" for="bindPhone" style="padding-right: 8px;line-height: 34px;">手机号码</label>
                        <div class="col-xs-5 col-sm-2" style="padding-left: 0px;padding-right: 2px;">
                            <input class="form-control captcha-phone" id="phone" type="text" value="${phone}" name="phone" style="padding: 6px;" readonly="readonly">
                        </div>
                        <div class="col-xs-4 col-sm-1" style="padding-left: 10px;line-height: 34px;">
                            <input class="btn btn-primary table-btn" id="yzmTime" type="button" onclick="getPhoneCaptcha(${phone})" value="获取验证码">
                        </div> 
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label" for="bindCaptcha">短信验证码</label>
                    <input class="form-control" id="bindCaptcha" type="text" name="bind_captcha" value="" placeholder="短信验证码">
                </div>
                <div class="form-group">
                    <label class="control-label" for="bindPasswd">密码</label>
                    <input class="form-control" id="password" type="password" name="password" value="" placeholder="长度8-20位，不能含有空格">
                </div>
                <div class="form-group">
                    <label class="control-label" for="bindRPasswd">确认新密码</label>
                    <input class="form-control" id="repassword"type="password" name="repassword" placeholder="长度8-20位，不能含有空格">
                </div>
                <div class="form-group">
                    <input class="col-sm-12 col-xs-12 btn control-btn" id="bindPhoneBtn" type="button" value="绑定手机" onclick="javascript:$('#storeForm').submit();">
                </div>
            </form>
</div>
<script>
var validateForm= $(function() {
		$('#storeForm').validate(
				{
					errorElement : 'div',
					errorClass : 'help-block',
					focusInvalid : false,
					ignore : "",
					rules : {
						phone : {
							required : true
						} ,
						password : {
                            required : true,
                            minlength : 6
                        },
                        repassword : {
                            required : true,
                            minlength : 6,
                            equalTo : "#password"
                        },
					},
					messages : {
						phone : "请填写手机号",
						password : {
	                        required : "请填写密码",
	                        minlength : "密码长度不能少于6个字符"
	                    },
	                    repassword : {
	                        required : "请再次填写密码",
	                        minlength : "密码长度不能少于6个字符",
	                        equalTo : "两次填写密码不一致，请重新填写"
	                    },
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
//提交
var submits=function (){
	 
	 var url="${ctx}/user/submitEdit.html";
	 var phone =$("#phone").val();  
	 var code =$("#bindCaptcha").val();  
	 var pwd =$("#password").val(); 
	 var repassword =$("#repassword").val(); 
	 if(phone==""){
		 layer.msg("手机号不能为空！", {icon: 5,time:1000});
		 return;
	 }
	 if(code==""){
		 layer.msg("验证码不能为空！", {icon: 5,time:1000});
		 return;
	 }
	 if(pwd==""){
		 layer.msg("密码不能为空！", {icon: 5,time:1000});
		 return;
	 }
	 debugger;
	 $.ajax({
        type: "POST",
        url: url,
        data:{"code":code,
	          "pwd":pwd,
	          "phone":phone},
        dataType: "json",
        success: function (msg) {
       	 if (msg.success==true){
       		 layer.msg(msg.message, {icon: 6,time:1000});
       		 window.setInterval(function (){
 		        var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
 		        parent.layer.close(index);
 		  }, 500)
       	 }else {
       		 layer.msg(msg.message, {icon: 5,time:1000});
       	 }
        }
        
    }); 
}
//获取短信验证
function getPhoneCaptcha(phone){
	 var url="${ctx}/user/sendSms.html";
	 $.ajax({
	        type: "POST",
	        url: url,
	        data:{"phone":phone},
	        dataType: "json",
	        success: function (msg) {
	       	 if (msg.success==true){
	       		layer.msg(msg.message, {icon: 6,time:1000});
	       		setCaptchaTime(this);
	       	 }else {
	       		 layer.msg(msg.message, {icon: 5,time:1000});
	       	 }
	        }
	        
	    });
}

var count = 60;
function setCaptchaTime(obj){
    if (count==0){
        $("#yzmTime").prop('disabled', false).val('获取验证码');
        count = 60;
        //$("#yzmTime").removeClass("colors");	
        return;
    }else{
    	//$("#yzmTime").addClass("colors");	
        $("#yzmTime").prop('disabled', true).val('获取('+count+'s)');
        count--;
        setTimeout(function(){setCaptchaTime(obj)}, 1000);
    }
}
</script>