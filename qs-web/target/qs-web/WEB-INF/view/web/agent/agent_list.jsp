<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<%--<%@include file="/common/common.jspf" %>--%>
<script type="text/javascript"
        src="${pageContext.request.contextPath }/resources/js/customer/web/agent_list.js"></script>
<div class="page-content">
    <div class="page-header">
        <h1 id="pageHeader">
            商务明细
        </h1>
    </div>
    <div class="row" style="margin-top:5px;">
        <div class="col-xs-12">
            <ul id="myTab" class="nav nav-tabs">
                <li class="active" id="li1"><a href="#listAgent" data-toggle="tab">商务明细</a></li>
                <li id="li2"><a href="#addAgent" data-toggle="tab">添加商务专员</a></li>
                <li id="li3"><a href="#achievement" data-toggle="tab">各地区业绩</a></li>
            </ul>

            <div id="myTabContent" class="tab-content">
                <div class="tab-pane fade in active" id="listAgent">
                    <div class="row">
                        <div class="col-xs-12">
                            <!-- PAGE CONTENT BEGINS -->
                            <div class="widget-box transparent ui-sortable-handle"
                                 style="opacity: 1; z-index: 0;">
                                <div class="widget-header">
                                    <%--<h4 class="widget-title lighter">APK更新日志</h4>--%>
                                    <div class="widget-toolbar no-border">
                                        <a href="#" data-action="fullscreen" class="orange2">
                                            <i class="ace-icon fa fa-arrows-alt"></i>
                                        </a>
                                        <a href="#" data-action="collapse" class="green">
                                            <i class="ace-icon fa fa-chevron-up"></i>
                                        </a>
                                    </div>
                                </div>
                                <a class="btn btn-warning" id="businessUrl" style="visibility: hidden;" target="_blank" href="http://127.0.0.1:8080/business/checkGameUi.html?id=1"><span  id="selectBusinessById">查看商务后台</span></a>
								<!-- target="view_window" -->
                                <div class="widget-body" style="display: block;">
                                    <div class="widget-main padding-6 no-padding-left no-padding-right">
                                        <input id="pageNum" type="hidden" value="${page.pageNum }">
                                        <input id="pageSize" type="hidden" value="${page.pageSize }">
                                        <input id="orderByColumn" type="hidden" value="${page.orderByColumn }">
                                        <input id="orderByType" type="hidden" value="${page.orderByType }">
                                        <div id="dtGridContainer" class="dlshouwen-grid-container"></div>
                                        <div id="dtGridToolBarContainer" class="dlshouwen-grid-toolbar-container"></div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="tab-pane fade" id="addAgent">
                    <div class="row">
                        <div class="col-xs-12">
                            <!-- PAGE CONTENT BEGINS -->
                            <form class="form-horizontal" role="form" id="agentForm">

                                <div class="form-group">
                                    <label class="control-label col-sm-1 no-padding-right">账号</label>
                                    <div class="col-sm-5">
                                        <div class="clearfix">
                                            <input class="form-control" name="account"
                                                   type="text" id="account"
                                                   placeholder="手机/邮箱..."/>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-sm-1 no-padding-right">密码</label>
                                    <div class="col-sm-5">
                                        <div class="clearfix">
                                            <input class="form-control" name="passWord"
                                                   type="passWord" id="passWord"
                                                   placeholder="长度6-20位..."/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-1 no-padding-right">重复密码</label>
                                    <div class="col-sm-5">
                                        <div class="clearfix">
                                            <input class="form-control" name="confirmPwd"
                                                   type="password" id="confirmPwd"
                                                   placeholder="长度6-20位..."/>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-1 no-padding-right">真实姓名</label>
                                    <div class="col-sm-5">
                                        <div class="clearfix">
                                            <input class="form-control" name="name" type="text" id="name"
                                                   placeholder="真实姓名..."/>
                                        </div>
                                    </div>
                                </div>


                               <%-- <input type="hidden" value="0" id="company" name="company"/>--%>
                               <div class="form-group">
                                    <label class="control-label col-sm-1 no-padding-right">所属分公司</label>
                                    <div class="col-sm-5">
                                        <div class="clearfix">
                                            <select class="form-control" id="company"
                                                    name="company" style="width: 100%">
                                                <c:if test="${!empty appCompanyList}">
                                                    <c:forEach var="app" items="${appCompanyList}" varStatus="status">
                                                        <c:if test="${status.first}">
                                                            <option value="${app.cid}" selected="selected">
                                                                    ${app.cname}
                                                            </option>
                                                        </c:if>
                                                        <c:if test="${!status.first}">
                                                            <option value="${app.cid}">
                                                                    ${app.cname}
                                                            </option>
                                                        </c:if>
                                                    </c:forEach>
                                                </c:if>
                                                <c:if test="${empty appCompanyList}">
                                                    <option value="" selected="selected">
                                                        未发现分公司
                                                    </option>
                                                </c:if>
                                                <%--<option value="0" selected="selected">
                                                    汕头分公司
                                                </option>--%>
                                                <%--<option value="1">
                                                    长沙分公司
                                                </option>
                                                <option value="2">
                                                    娄底分公司
                                                </option>
                                                <option value="3">
                                                    邵阳分公司
                                                </option>--%>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="control-label col-sm-1 no-padding-right">备注</label>
                                    <div class="col-sm-5">
                                        <textarea id="remark" class="form-control" name="remark"
                                                  placeholder="可以不填..."
                                                  style="width: 100%;height: 100%"></textarea>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-3">

                                    </div>
                                    <div class="col-sm-1">
                                        <button class="btn-success btn-sm" name="add"
                                                type="button" id="add"
                                                onclick="javascript:$('#agentForm').submit();">添加
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="tab-pane fade" id="achievement">
                    <div class="row">
                        <div class="col-xs-12">
                            <!-- PAGE CONTENT BEGINS -->
                           <!--  achievement -->
                        </div>
                    </div>
                </div>

            </div>

            <div class="hr hr-dotted"></div>
        </div>
    </div>
</div>
<script>


    $(function () {

        $('#agentForm').validate({
            errorElement: 'div',
            errorClass: 'help-block',
            focusInvalid: false,
            ignore: "",
            rules: {
                account: {
                    required: true
                },
                passWord: {
                    required: true,
                    rangelength: [6, 20]
                },
                name: {
                    required: true
                },
                confirmPwd: {
                    required: true,
                    rangelength: [6, 20],
                    equalTo: "#passWord"
                },
                company:{
                    required: true
                }
            },
            messages: {
                account: "账户名称不能为空",
                passWord: {
                    required: "密码不能为空",
                    rangelength: "长度为6-20位"
                },
                confirmPwd: {
                    required: "确认密码不能为空",
                    rangelength: "长度为6-20位"
                },
                name: "真实姓名不能为空",
                company:'请选择公司'
            },
            highlight: function (e) {
                $(e).closest('.form-group').removeClass('has-info').addClass('has-error');
            },
            success: function (e) {
                $(e).closest('.form-group').removeClass('has-error').addClass('has-success');
                $(e).remove();
            },
            errorPlacement: function (error, element) {
                error.insertAfter(element.parent());
            },
            submitHandler: function (form) {

                var id = $("#id").val();
                var url = "";
                if (id != undefined) {
                    url = '/agent/business/edit.html';
                } else {
                    url = '/agent/business/add.html';
                }
                //webside.common.commit('agentForm', url, '/agent/business/listUi.html');
                websideCommit('agentForm', url, '/agent/business/listUi.html');
            }
        });

        $('#li1').on('click', function () {
            $('#pageHeader').text("商务明细");
        });
        $('#li2').on('click', function () {
            $('#pageHeader').text("添加商务专员");
        });
        $('#li3').on('click', function () {
            $('#pageHeader').text("各地区业绩");
        });

        function websideCommit(formId, commitUrl, jumpUrl) {
            //组装表单数据
            var index;
            var data = $("#" + formId).serialize();
            if (undefined != $("#pageNum").val()) {
                jumpUrl = jumpUrl + '?page=' + $("#pageNum").val() + '&rows='
                    + $("#pageSize").val() + '&sidx=' + $("#orderByColumn").val()
                    + '&sord=' + $("#orderByType").val();
            }
            $.ajax({
                type : "POST",
                url : sys.rootPath + commitUrl,
                data : data,
                dataType : "json",
                beforeSend : function() {
                    index = layer.load();
                },
                success : function(resultdata) {
                    layer.close(index);
                    if (resultdata.success) {
                        layer.msg(resultdata.message, {
                            icon : 1
                        });
                        //webside.common.loadPage(jumpUrl);
                        //$(".page-content").load(sys.rootPath + jumpUrl);
                        // /agent/business/
                        $('#addAgent').removeClass('active');
                        //$('#listAgent').addClass('active');
                        grid.refresh(true);
                        $("#agentForm input[type='text'] , #agentForm textarea").each(function(){
                            $(this).val("");
                        });
                        webside.common.loadPage(jumpUrl);
                    } else {
                        layer.msg(resultdata.message, {
                            icon : 5
                        });
                    }
                },
                error : function(data, errorMsg) {
                    layer.close(index);
                    layer.msg(data.responseText, {
                        icon : 2
                    });
                }
            });
        }

        // 手机号码验证
      /*  jQuery.validator.addMethod("isMobile", function(value, element) {
            var length = value.length;
            var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
            return this.optional(element) || (length == 11 && mobile.test(value));
        }, "请正确填写您的手机号码");*/

        //jQuery.validator.
    });

    var jsonBuilder = {//layer.msg('更新成功!', {icon: 6});
        add: function (key, value) {
            this.startJson += "\"" + key + "\"" + ":" + "\"" + value + "\",";
            return this;
        },
        startJson: "{",
        getJson: function () {
            var aa = this.startJson.substring(0, this.startJson.length - 1) + "}";
            this.startJson = "{";
            return aa;
        }
    }

</script>

