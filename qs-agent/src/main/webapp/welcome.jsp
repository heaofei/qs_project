<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>

</head>
<!-- <div class="page-header">
	<h1>
		欢迎页 <small> <i class="ace-icon fa fa-angle-double-right"></i>
			用户信息
		</small>
	</h1>
</div> -->
<div class="row" style="padding-top: 20px">
<table class="table" >
   <tbody>
        <tr style="width: 10%">
	                      <th rowspan="3" style="text-align: center;"><img src="${memberFides.icon}" alt="头像"  style="width: 50px; height: 50px;"/></th> 
	                      <th>用户ID：</th> 
	                      <th> ${userEntity.mid }</th></tr>
                      <tr style="width: 30%"> 
	                      <th>邀请码：</th> 
	                      <th>${memberInvite.invitecode}</th>
	                  </tr>
                      <tr style="width: 30%"> 
	                      <th>创建时间：</th> 
	                      <th>${userEntity.mktime}
	                      </th>
                      </tr>
   </tbody>
</table>
		 <%-- <div class="_qs_table">
		                <table style="">sessionScope.
		                      <tr style="width: 10%">
			                      <th rowspan="3"><img src="${ctx}/resources/images/user.jpg" alt="头像" /></th> 
			                      <th>用户ID：</th> 
			                      <th> ${userEntity.mid }</th></tr>
		                      <tr style="width: 30%"> 
			                      <th>邀请码：</th> 
			                      <th>${memberInvite.invitecode}</th>
			                  </tr>
		                      <tr style="width: 30%"> 
			                      <th>创建时间：</th> 
			                      <th>${userEntity.mktime}
			                      </th>
		                      </tr>
		                </table>
		   		</div> --%>
</div>


</html>