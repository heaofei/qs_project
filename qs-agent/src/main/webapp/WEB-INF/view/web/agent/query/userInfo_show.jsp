<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<style>
.bg{padding-top: 10px;
    background: #f5f5f5;}
</style>
 <div class="container">
        <div class="page-title">
            <h4>查看个人资料</h4>
        </div>
        <div class="well">
                       温馨提示：若要修改手机、银行等信息，请联系官方QQ：<b>976937819</b>。
        </div>
        <div class="well">
            <div class="row bg">
                <label class="col-xs-4">用户ID</label>
                <div class="col-xs-8"> ${memberAgents.mid}</div>
            </div>
            <div class="row bg">
                <label class="col-xs-4">游戏昵称</label>
                <div class="col-xs-8">${memberFides.name }</div>
            </div>
            <div class="row bg">
                <label class="col-xs-4">真实姓名</label>
                <div class="col-xs-8"> ${memberAgents.realname}</div>
            </div>
            <div class="row bg">
                <label class="col-xs-4">手机号码</label>
                <div class="col-xs-8"> ${memberAgents.phone}</div>
            </div>
            <div class="row bg">
                <label class="col-xs-4">所属地区</label>
                <div class="col-xs-8">${area.aname }&nbsp;&nbsp;${area.province }&nbsp;&nbsp;${area.city }</div>
            </div>
            <div class="row bg">
                <label class="col-xs-4">银行卡号</label>
                <div class="col-xs-8">${memberAgents.bankcard }</div>
            </div>
            <div class="row bg">
                <label class="col-xs-4">开户银行</label>
                <div class="col-xs-8">
                   ${memberAgents.bank}
                </div>
            </div>
        </div>
    </div>