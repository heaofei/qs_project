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

<div class="container clear">
    <c:if test="${!empty gameName}">
        <div class="panel  panel-info" style="margin-bottom: 5px;">
            <div class="panel-heading">
                <h4 class="panel-title" style="text-align: center;">当前游戏: ${gameName}&nbsp;
                    <button type="button" class="btn btn-sm btn-success" style="border-width:0;border-radius: 5px;" onclick="chooseGame()">选择游戏</button>
                </h4>
            </div>
                <%--<div class="panel-body">
                    这是一个基本的面板
                </div>--%>
        </div>
    </c:if>
</div>
<div class="container clear">

    <ul class="nav nav-pills nav-stacked menu">
        <c:forEach var="resource"   items="${list }"  varStatus="s">
            <c:if test="${resource.parentId !=null and resource.parentId !=''}">
                <li>
                    <a href="javascript:webside.common.loadPage('${resource.sourceUrl}')" nav-menu="商务后台,${resource.name },${resource.sourceUrl }">
                        <span class="${resource.icon }"></span>&nbsp;&nbsp;${resource.name }
                        <span class="icon-circle" style="font-size: 12px;color:red;"></span>
                        <span class="fa fa-angle-right icon-2x show-page" style="font-size: 25px;"></span>
                    </a>
                </li>
            </c:if>
        </c:forEach>
    </ul>
</div>
<!--  <div class="footer">
       <div class="footer-inner">
           <div class="footer-content">
                   <span class="bigger-110"> <span class="blue bolder">版权所有&copy; 乐玩游戏网络技术有限公司</span>
                   </span> &nbsp; &nbsp; <span class="action-buttons">
                   </span>
           </div>
       </div>
   </div>
-->
</html>
<script>
    function chooseGame() {
        window.location.href = "${ctx}/checkGameUi.html";
    }
</script>
