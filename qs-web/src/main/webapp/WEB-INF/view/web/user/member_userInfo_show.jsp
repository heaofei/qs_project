<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%--<%@include file="/common/common.jspf" %>--%>

<div class="page-header">
    <div class="page-header">
        <h1 id="pageHeader">
            用户资料
        </h1>
    </div>
    <button id="btn" type="button" onclick="webside.common.loadPage('/member/managementUi.html<c:if
            test="${!empty id}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }&id=${id}</c:if>')"
            class="btn btn-info btn-sm">
        <i class="fa fa-undo"></i>&nbsp;返回
    </button>&nbsp;
    <button id="cardRecord" type="button" onclick="cardRecord(${id})"
            class="btn btn-primary btn-sm">
        <i class="fa fa-user-secret"></i>&nbsp;牌局记录
    </button>
    <button id="goldOrigin" type="button" class="btn btn-primary btn-sm btn-purple"
            onclick="goldOrigin(${id})" >
        <i class="fa fa-user-secret"></i>&nbsp;金币来源
    </button>
	<!--     代理商修改邀请码 -->
    <shiro:hasPermission name="agent:inviteCode"> 
        <c:if test="${!empty memberFides.memberAgents}">
            <button type="button" class="btn btn-primary btn-sm btn-danger" onclick="editUserInviteCode(${id})">
                <i class="fa fa-user-secret"></i>&nbsp;修改邀请码
            </button>
        </c:if>
    </shiro:hasPermission>
    <shiro:hasPermission name="agent:inviteCode">
        <button type="button" class="btn btn-primary btn-sm btn-grey" onclick="cancelOrBindInvite(${id})">
            <i class="fa fa-user-secret"></i>&nbsp;取消/绑定邀请人
        </button>
    </shiro:hasPermission>
    <shiro:hasPermission name="agent:inviteCode">
        <c:if test="${!empty memberFides.memberAgents}">
            <button type="button" class="btn btn-primary btn-sm btn-success" onclick="cancelAgent(${id})">
                <i class="fa fa-user-secret"></i>&nbsp;取消代理商
            </button>
         </c:if>
    </shiro:hasPermission>
    <shiro:hasPermission name="agent:inviteCode">
        <c:if test="${!empty memberFides.memberAgents}">
            <button type="button" class="btn btn-primary btn-sm btn-lighter" onclick="cancelAgentMembershipBinding(${id})">
                <i class="fa fa-user-secret"></i>&nbsp;取消代理商会员绑定
            </button>
        </c:if>
    </shiro:hasPermission>
    <shiro:hasPermission name="agent:inviteCode">
        <button type="button" class="btn btn-primary btn-sm btn-pink" onclick="addGold(${id})">
            <i class="fa fa-user-secret"></i>&nbsp;增/减金币
        </button>
    </shiro:hasPermission>
</div>

<div class="row" style="margin-top:5px;">
    <div class="col-xs-12">
        <form id="storeForm" name="storeForm" class="form-horizontal" role="form" method="post">
            <c:if test="${!empty memberFides}">
                <input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }">
                <input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }">
                <input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }">
                <input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }">
                <input type="hidden" name="id" id="id" value="${memberFides.id }">
            </c:if>

            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">用户名</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <input class="form-control" name="name"
                               type="text" id="name" value="${memberFides.name}"
                               placeholder="用户名..."  />
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">性别</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <c:if test="${memberFides.sex eq 0}">
                            <input class="form-control" name="sex"
                                   type="text" id="sex" value="男"
                                   placeholder="性别..."  />
                        </c:if>
                        <c:if test="${memberFides.sex eq 1}">
                            <input class="form-control" name="sex"
                                   type="text" id="sex" value="女"
                                   placeholder="性别..."  />
                        </c:if>
                        <c:if test="${memberFides.sex != 1 and memberFides.sex != 0}">
                            <input class="form-control" name="sex"
                                   type="text" id="sex" value=""
                                   placeholder="性别..."  />
                        </c:if>
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">生日</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <input class="form-control" name="born" value="${memberFides.born}"
                               type="text" id="born"
                               placeholder="生日..."  />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">城市</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <input class="form-control" name="city"
                               type="text" id="city" value="${memberFides.city}"
                               placeholder="城市..."  />
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">头像</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <input class="form-control" name="icon" value="${memberFides.icon}"
                               type="text" id="icon"
                               placeholder="头像..."  />
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">邀请人</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <input class="form-control" name="invite"
                               type="text" id="invite" value="${memberFides.invite}"
                               placeholder="邀请人..."  />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">用户所在区域或组</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <input class="form-control" name="gameArea" value="${memberFides.gameArea}"
                               type="text" id="gameArea"
                               placeholder="用户所在区域或组..."  />
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">用户状态</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <input class="form-control" name="status"
                               type="text" id="status" value="${memberFides.status}"
                               placeholder="用户状态..."  />
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">用户加入时间</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <input class="form-control" name="mtime" value="${memberFides.mtime}"
                               type="text" id="mtime"
                               placeholder="用户加入时间..."  />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">用户邮箱</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <input class="form-control" name="email"
                               type="text" id="email" value="${memberFides.email}"
                               placeholder="用户邮箱..."  />
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">绑定时间</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <input class="form-control" name="bindtime" value="${memberFides.bindtime}"
                               type="text" id="bindtime"
                               placeholder="绑定时间..."  />
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">手机号</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <input class="form-control" name="tel"
                               type="text" id="tel" value="${memberFides.tel}"
                               placeholder="手机号..."  />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">mid</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <input class="form-control" name="mid" value="${memberFides.mid}"
                               type="text" id="mid"
                               placeholder="mid..."  />
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">sitemid</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <input class="form-control" name="sitemid"
                               type="text" id="sitemid" value="${memberFides.sitemid}"
                               placeholder="sitemid..."  />
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">openid</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <input class="form-control" name="idCard" type="text"
                               value="${memberFides.idCard}" id="idCard"
                               placeholder="openid..."  />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <%--<label class="control-label col-sm-1 no-padding-right">黄钻等级</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <input class="form-control" name="yellowvip"
                               type="text" id="yellowvip" value="${memberFides.yellowvip}"
                               placeholder="黄钻等级..."  />
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">是否年费黄钻</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <input class="form-control" name="isyearvip" type="text"
                               value="${memberFides.isyearvip}" id="isyearvip"
                               placeholder="是否年费黄钻..."  />
                    </div>
                </div>--%>
                <label class="control-label col-sm-1 no-padding-right">所在IP</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <input class="form-control" name="address"
                               type="text" id="address" value="${memberFides.address}"
                               placeholder="所在IP..."  />
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">sesskey</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <input class="form-control" name="sesskey" type="text"
                               value="" id="sesskey"
                               placeholder="sesskey..."  />
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">设备ID</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <input class="form-control" name="passwd"
                               type="text" id="passwd" value="${memberFides.sbId}"
                               placeholder="设备ID..."  />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">代理商级别</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <input class="form-control" name="alevel" type="text"
                               value="${memberFides.alevel}" id="alevel"
                               placeholder="代理商级别..."  />
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">成为代理商时间</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <input class="form-control" name="mktime"
                               type="text" id="mktime" value="${memberFides.mktime}"
                               placeholder="成为代理商时间..."  />
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">所属商务</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <input class="form-control" name="belongid" type="text"
                               value="${memberFides.businessName}" id="belongid"
                               placeholder="所属商务..."  />
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">设备OS</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <input class="form-control" name=""
                               type="text" id="" value=""
                               placeholder="设备OS..."  />
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">设备型号</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <input class="form-control" name="" type="text"
                               value="" id=""
                               placeholder="设备型号..."  />
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">是否代开房</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <c:if test="${empty memberFides.agentMids}">
                            <input class="form-control" name="" type="text"
                                   value="否" id=""
                                   placeholder="是否代开房..."  />
                        </c:if>
                        <c:if test="${!empty memberFides.agentMids}">
                            <input class="form-control" name="" type="text"
                                   value="是" id=""
                                   placeholder="是否代开房..."  />
                        </c:if>
                    </div>
                </div>
            </div>

            <%--<div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">设备型号</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <input class="form-control" name="" type="text"
                               value="" id=""
                               placeholder="设备型号..."  />
                    </div>
                </div>
               <label class="control-label col-sm-1 no-padding-right">来源</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <input class="form-control" name="comeFrom" type="text"
                               value="${comeFrom}" id="comeFrom"
                               placeholder="来源..."  />
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">是否代开房</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <input class="form-control" name="" type="text"
                               value="" id=""
                               placeholder="是否代开房..."  />
                    </div>
                </div>
            </div>--%>

            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">金币</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <input class="form-control" name="gold" type="text"
                               value="${memberFides.gold}" id="gold"
                               placeholder="金币..."  />
                    </div>
                </div>
               <%-- <label class="control-label col-sm-1 no-padding-right">VIP等级</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <input class="form-control" name="vip" type="text"
                               value="${memberFides.vip}" id="vip"
                               placeholder="VIP等级..."  />
                    </div>
                </div>--%>
                <label class="control-label col-sm-1 no-padding-right">用户登录次数</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <input class="form-control" name="totalLgin" type="text"
                               value="${memberFides.totalLgin}" id="totalLgin"
                               placeholder="用户登录次数..."  />
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">用户最后登录时间</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <input class="form-control" name="lastLogin" type="text"
                               value="${memberFides.lastLogin}" id="lastLogin"
                               placeholder="用户最后登录时间..."  />
                    </div>
                </div>
            </div>

            <%--<div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">用户最后登录时间</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <input class="form-control" name="lastLogin" type="text"
                               value="${memberFides.lastLogin}" id="lastLogin"
                               placeholder="用户最后登录时间..."  />
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">银行存款</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <input class="form-control" name="bankAssets" type="text"
                               value="${memberFides.bankAssets}" id="bankAssets"
                               placeholder="银行存款..."  />
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">银行密码</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <input class="form-control" name="bankpasswd" type="text"
                               value="${memberFides.bankpasswd}" id="bankpasswd"
                               placeholder="银行密码..."  />
                    </div>
                </div>
            </div>--%>

            <div class="form-group">
                <%--<label class="control-label col-sm-1 no-padding-right">经验值</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <input class="form-control" name="" type="text"
                               value="" id=""
                               placeholder="经验值..."  />
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">积分</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <input class="form-control" name="" type="text"
                               value="" id=""
                               placeholder="积分..."  />
                    </div>
                </div>--%>
                <label class="control-label col-sm-1 no-padding-right">当天局数</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <input class="form-control" name="" type="text"
                               value="" id=""
                               placeholder="当天局数..."  />
                    </div>
                </div>
                    <label class="control-label col-sm-1 no-padding-right">总玩牌局数</label>
                    <div class="col-sm-3">
                        <div class="clearfix">
                            <input class="form-control" name="" type="text"
                                   value="" id=""
                                   placeholder="总玩牌局数..."  />
                        </div>
                    </div>
                    <label class="control-label col-sm-1 no-padding-right">总充值数</label>
                    <div class="col-sm-3">
                        <div class="clearfix">
                            <input class="form-control" name="regip" type="text"
                                   value="${memberFides.regip}" id="regip"
                                   placeholder="总充值数..."  />
                        </div>
                    </div>
            </div>

            <%--<div class="form-group">
               <label class="control-label col-sm-1 no-padding-right">QQ号</label>
                <div class="col-sm-3">
                    <div class="clearfix">
                        <input class="form-control" name="qq" type="text"
                               value="${memberFides.qq}" id="qq"
                               placeholder="QQ号..."  />
                    </div>
                </div>
            </div>--%>
     

        </form>
    </div>

    <div class="hr hr-dotted"></div>
</div>


