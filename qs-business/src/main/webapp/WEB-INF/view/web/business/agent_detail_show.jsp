<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%--<%@include file="/common/common.jspf" %>--%>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/resources/js/customer/web/agent_query_list.js"></script>
<script src="${ctx}/resources/js/customer/index/index_list.js" type="text/javascript"></script>
<div class="row">
    <div class="col-sm-1">
        <button id="btn" type="button" onclick="webside.common.loadPage('/agentQuery/queryAgentsUi.html<c:if
                test="${!empty record}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')"
                class="btn btn-info btn-sm">
            <i class="fa fa-undo"></i>&nbsp;返回
        </button>
    </div>
    <div class="col-sm-11"></div>
</div>
<hr/>

<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">代理商详情</h3>
    </div>
    <div class="panel-body">
        <div class="row" style="margin-top:5px;">
            <div class="col-xs-12" >
                <form id="memberForm" name="memberForm" class="form-horizontal" role="form" method="post">

                    <div class="form-group">
                        <div class="col-sm-1">

                        </div>
                        <label class="control-label col-sm-5 no-padding-right" style="text-align: left;" >
                            归属代理商ID：
                            <c:if test="${!empty record}">
                                <c:if test="${!empty record.agentBusinessInfo}">
                                    ${record.agentBusinessInfo.firstmid}
                                </c:if>
                            </c:if>
                        </label>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-1">

                        </div>
                        <label class="control-label col-sm-5 no-padding-right no-padding-right" style="text-align: left;">
                            归属代理商真实姓名：
                            <c:if test="${!empty record}">
                                ${record.realNameBind}
                            </c:if>
                        </label>
                    </div>
                    <hr/>
                    <div class="form-group">
                        <div class="col-sm-1">

                        </div>
                        <label class="control-label col-sm-5 no-padding-right" style="text-align: left;" >
                            游戏ID：
                            <c:if test="${!empty record}">
                                <c:if test="${!empty record.agentBusinessInfo}">
                                    ${record.agentBusinessInfo.mid}
                                </c:if>
                            </c:if>
                        </label>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-1">

                        </div>
                        <label class="control-label col-sm-5 no-padding-right" style="text-align: left;" >
                            游戏昵称：
                            <c:if test="${!empty record}">
                                ${record.gameName}
                            </c:if>
                        </label>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-1">

                        </div>
                        <label class="control-label col-sm-5 no-padding-right" style="text-align: left;" >
                            累计充值：
                            <c:if test="${!empty record}">
                                ${record.payTotal}
                            </c:if>
                        </label>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-1">

                        </div>
                        <label class="control-label col-sm-5 no-padding-right" style="text-align: left;" >
                            累计招募人数：
                            <c:if test="${!empty record}">
                                ${record.inviteTotal}
                            </c:if>
                        </label>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-1">

                        </div>
                        <label class="control-label col-sm-5 no-padding-right" style="text-align: left;" >
                            真实姓名：
                            <c:if test="${!empty record}">
                                <c:if test="${!empty record.agentBusinessInfo}">
                                    ${record.agentBusinessInfo.realname}
                                </c:if>
                            </c:if>
                        </label>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-1">

                        </div>
                        <label class="control-label col-sm-5 no-padding-right" style="text-align: left;" >
                            加入时间：
                            <c:if test="${!empty record}">
                                <c:if test="${!empty record.agentBusinessInfo}">
                                    ${record.agentBusinessInfo.comeTime}
                                </c:if>
                            </c:if>
                        </label>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-1">

                        </div>
                        <label class="control-label col-sm-5 no-padding-right" style="text-align: left;" >
                            联系电话：
                            <c:if test="${!empty record}">
                                <c:if test="${!empty record.agentBusinessInfo}">
                                    ${record.agentBusinessInfo.phone}
                                </c:if>
                            </c:if>
                        </label>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-1">

                        </div>
                        <label class="control-label col-sm-5 no-padding-right" style="text-align: left;" >
                            银行账号：
                            <c:if test="${!empty record}">
                                <c:if test="${!empty record.agentBusinessInfo}">
                                    ${record.agentBusinessInfo.bankcard}
                                </c:if>
                            </c:if>
                        </label>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-1">

                        </div>
                        <label class="control-label col-sm-5 no-padding-right" style="text-align: left;" >
                            开户银行：
                            <c:if test="${!empty record}">
                                <c:if test="${!empty record.agentBusinessInfo}">
                                    ${record.agentBusinessInfo.bank}
                                </c:if>
                            </c:if>
                        </label>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-1">

                        </div>
                        <label class="control-label col-sm-1 no-padding-right" style="text-align: left;" >
                            <button type="button" class="btn btn-success" onclick="goToAgent()">查看代理商业绩</button>
                        </label>
                        <div class="col-sm-10">
                            <div class="clearfix">

                            </div>
                        </div>
                    </div>

                </form>
                <div class="hr hr-dotted"></div>
            </div>
        </div>

        <%--<div class="center">
            <button id="btn" type="button" onclick="webside.common.loadPage('/agentQuery/queryAgentsUi.html<c:if
                    test="${!empty record}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')"
                    class="btn btn-info btn-sm">
                <i class="fa fa-undo"></i>&nbsp;返回
            </button>
        </div>--%>
    <a class="btn btn-warning" id="businessUrl" style="visibility: hidden;" target="_blank">
        <span  id="redirectUrl"></span>
    </a>
    </div>
</div>

<script>
    function goToAgent() {
        var url = "${ctx}/agentQuery/setAgentLoginUserKey.html";
        $.ajax({
            type : "POST",
            url : url,
            data : {
                sitemid:'${sitemid}'
            },
            dataType : "json",
            success : function(msg) {
                if (msg.success == true) {
                    if (ismobile(null) == '0') {
                        window.location.href = msg.redirectUrl;
                    }else{
                        $('#businessUrl').attr('href',msg.redirectUrl);
                        $("#redirectUrl").click();
                    }
                } else {
                    layer.msg("请尝试安全登入！", {
                        icon : 5,
                        time : 500
                    });
                }
            }

        });

        //$('#redirectUrl').click();
    }

    /**
     * [isMobile 判断平台]
     * @param test: 0:iPhone    1:Android
     */
    function ismobile(test){
        var u = navigator.userAgent, app = navigator.appVersion;
        if(/AppleWebKit.*Mobile/i.test(navigator.userAgent) || (/MIDP|SymbianOS|NOKIA|SAMSUNG|LG|NEC|TCL|Alcatel|BIRD|DBTEL|Dopod|PHILIPS|HAIER|LENOVO|MOT-|Nokia|SonyEricsson|SIE-|Amoi|ZTE/.test(navigator.userAgent))){
            if(window.location.href.indexOf("?mobile")<0){
                try{
                    if(/iPhone|mac|iPod|iPad/i.test(navigator.userAgent)){
                        return '0';
                    }else{
                        return '1';
                    }
                }catch(e){}
            }
        }else if( u.indexOf('iPad') > -1){
            return '0';
        }else{
            return '1';
        }
    }
</script>
