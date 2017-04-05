<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<link rel="stylesheet" href="${ctx}/resources/css/customer/commons.css"/>
</head>
<div class="row">
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
		
</div>

<div class="container clear">

 <ul class="nav nav-pills nav-stacked menu">
      <c:forEach var="resource"   items="${list }"  varStatus="s">
      <c:if test="${resource.parentId !=null and resource.parentId !=''}">
                <li>
                    <a href="javascript:webside.common.loadPage('${resource.sourceUrl}')" nav-menu="代理商后台,${resource.name },${resource.sourceUrl }">
                        <span class="${resource.icon }"></span>&nbsp;&nbsp;${resource.name }
                        	<span class="icon-circle" style="font-size: 12px;color:red;"></span>
                        <span class="icon-angle-right icon-2x show-page"></span>
                    </a>
                </li>
                </c:if>
      </c:forEach>
    </ul> 
</div>

     <div class="footer" style=" bottom:0;">
        <div class="footer-inner">
            #section:basics/footer
            <div class="footer-content">
					<span class="bigger-110"> <span class="blue bolder">版权所有&copy; 深圳市乐玩互娱网络技术有限公司</span>
					</span> &nbsp; &nbsp; <span class="action-buttons"> 
					</span>
            </div>

            /section:basics/footer
        </div>
    </div>  

</html>
