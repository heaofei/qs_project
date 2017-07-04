<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<script>
    $(function () {
        jeDate({
            dateCell: '#startTime',
            format:"YYYY-MM-DD hh:mm:ss",
            isinitVal:true,
            isTime:true,
            minDate:"2014-09-19 00:00:00"
        });
        jeDate({
            dateCell: '#endTime',
            format:"YYYY-MM-DD hh:mm:ss",
            isinitVal:true,
            isTime:true,
            minDate:"2014-09-19 00:00:00"
        });
    });
</script>

<div class="page-header">
    <h1>
        <c:if test="${empty record}">
            新增定时公告
        </c:if>
        <c:if test="${!empty record}">
            编辑定时公告
        </c:if>
    </h1>
</div>
<div class="row" style="margin-top:5px;">
    <div class="col-xs-12">
        <form id="storeForm" name="storeForm" class="form-horizontal" role="form" method="post">
            <c:if test="${!empty record}">
                <input type="hidden" id="pageNum" name="pageNum" value="${page.pageNum }">
                <input type="hidden" id="pageSize" name="pageSize" value="${page.pageSize }">
                <input type="hidden" id="orderByColumn" name="orderByColumn" value="${page.orderByColumn }">
                <input type="hidden" id="orderByType" name="orderByType" value="${page.orderByType }">
                <input type="hidden" name="id" id="id" value="${record.id }">
            </c:if>

            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">标题</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="title" id="title" type="text"
                               value="${record.title }" placeholder="标题..."/>
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">活动类型</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <select class="form-control" id="actiType" name="actiType" style="width: 100%">
                            <option value="1" <c:if test="${record.actiType eq '1' }">selected="selected"</c:if>>
                                官方公告
                            </option>
                            <option value="2" <c:if test="${record.actiType eq '2' }">selected="selected"</c:if>>
                                游戏活动
                            </option>
                            <option value="3" <c:if test="${record.actiType eq '3' }">selected="selected"</c:if>>
                                页面活动
                            </option>
                        </select>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">开始时间</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="startTime" id="startTime"
                               value="${record.startTime}"
                               placeholder="开始时间...">
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">截止时间</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="endTime" id="endTime"
                               value="${record.endTime}"
                               <%--value="<fmt:formatDate value="${record.etime }" pattern="yyyy-MM-dd HH:mm:ss"/>"--%>
                               placeholder="截止时间...">
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">间隔时间(单位:秒)</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="intervalTime" id="intervalTime" type="number"
                               value="${record.intervalTime }" placeholder="间隔时间..."/>
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">启用</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <select class="form-control" id="isEnable" name="isEnable" style="width: 100%">
                            <option value="0" <c:if test="${record.isEnable eq '0' }">selected="selected"</c:if>>
                                是
                            </option>
                            <option value="1" <c:if test="${record.isEnable eq '1' }">selected="selected"</c:if>>
                                否
                            </option>
                        </select>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">内容</label>
                <div class="col-sm-11">
                    <div class="clearfix">
                        <textarea class="form-control" id="content"
                                  name="content" placeholder="内容...">${record.content }</textarea>
                    </div>
                </div>
            </div>

        </form>
    </div>
</div>
<div class="hr hr-dotted"></div>
<div class="center">
    <button id="btnAdd" type="button" onclick="javascript:$('#storeForm').submit();" class="btn btn-success btn-sm">
        <i class="fa fa-user-plus"></i>&nbsp;
        <c:if test="${empty record}">
            添加
        </c:if>
        <c:if test="${!empty record}">
            保存
        </c:if>
    </button>
    <button id="btn" type="button" onclick="webside.common.loadPage('/noticeNew/listUi.html<c:if
            test="${!empty record}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')"
            class="btn btn-info btn-sm">
        <i class="fa fa-undo"></i>&nbsp;返回
    </button>
</div>

<script type="text/javascript">
    $(function () {

        $('#storeForm').validate({
            errorElement: 'div',
            errorClass: 'help-block',
            focusInvalid: false,
            ignore: "",
            rules: {
                title: {
                    required: true
                },
                actiType: {
                    required: true
                },
                startTime: {
                    required: true
                },
                endTime: {
                    required: true
                },
                intervalTime: {
                    required: true,
                    min:30
                },
                isEnable: {
                    required: true
                },
                content: {
                    required: true
                }
            },
            messages: {
                title: "请填写标题！",
                actiType: "请选择活动活动类型！",
                startTime: "请选择开始时间！",
                endTime: "请选择结束时间！",
                intervalTime: "请填写间隔时间，必须不小于30秒！",
                isEnable: "请选择是否启用！",
                content: "请填写内容！"
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
                var intervalTime = $('#intervalTime').val();
                if (intervalTime != undefined && intervalTime != '') {
                    if (intervalTime % 5 != 0) {
                        layer.msg("间隔时间必须为5的倍数!", {icon: 6});
                        return;
                    }
                }
                var id = $("#id").val();
                var url = "";
                if (id != undefined) {
                    url = '/noticeNew/edit.html';
                } else {
                    url = '/noticeNew/add.html';
                }
                webside.common.commit('storeForm', url, '/noticeNew/listUi.html');
            }
        });

    });
</script>