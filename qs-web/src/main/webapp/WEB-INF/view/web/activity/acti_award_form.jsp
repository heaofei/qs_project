<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>
<div class="page-header">
    <h1>
        <c:if test="${empty record}">
            新增奖品
        </c:if>
        <c:if test="${!empty record}">
            编辑奖品
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
                <label class="control-label col-sm-1 no-padding-right">奖品名称</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="name" id="name" type="text"
                               value="${record.name}" placeholder="奖品名称..."/>
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">所需积分</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="integral" id="integral" type="number"
                               value="${record.integral}" placeholder="所需积分..."/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">活动类型</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <select class="form-control" id="type" name="type" style="width: 100%">
                            <c:forEach items="${activityList}" var="act">
                                <option value="${act.code}"
                                        <c:if test="${record.type eq act.code}">selected="selected"</c:if>>${act.name}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">奖品数量</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <div class="clearfix">
                            <input class="form-control" name="awardNum" id="awardNum" type="number"
                                   value="${record.awardNum}" placeholder="奖品数量..."/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">是否需要审核</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <select class="form-control" id="review" name="review" style="width: 100%">
                            <option value="1" <c:if test="${record.review eq '1' }">selected="selected"</c:if>>
                                是
                            </option>
                            <option value="0" <c:if test="${record.review eq '0' }">selected="selected"</c:if>>
                                否
                            </option>
                        </select>
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">上传奖品图片</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input  class="btn-upload" id="imgUpload" type="file" onchange="uploadImg('imgUpload')" accept="image/*"  multiple="multiple"/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">限制领取次数</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <select class="form-control" id="limitNum" name="limitNum" style="width: 100%">
                            <option value="1" <c:if test="${record.limitNum eq '1' }">selected="selected"</c:if>>
                                是
                            </option>
                            <option value="0" <c:if test="${record.limitNum eq '0' }">selected="selected"</c:if>>
                                否
                            </option>
                        </select>
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right">可领取次数</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <input class="form-control" name="receiveNum" id="receiveNum" type="number"
                               value="${record.receiveNum}" placeholder="可领取次数..."/>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-1 no-padding-right">商品类型</label>
                <div class="col-sm-5">
                    <div class="clearfix">
                        <select class="form-control" id="remark" name="remark" style="width: 100%">
                            <option value="0" <c:if test="${record.remark eq '0' }">selected="selected"</c:if>>
                                非房卡
                            </option>
                            <option value="1" <c:if test="${record.remark eq '1' }">selected="selected"</c:if>>
                                房卡
                            </option>
                        </select>
                        <%--<input class="form-control" name="remark" id="remark" type="text"
                               value="${record.remark}" placeholder="备注..."/>--%>
                    </div>
                </div>
                <label class="control-label col-sm-1 no-padding-right" id="descrLabel" style="display: none;">房卡数量</label>
                <div class="col-sm-5" id="descrDiv" style="display: none;">
                    <div class="clearfix">
                        <input class="form-control" name="descr" id="descr" type="number"
                               value="${record.descr }" placeholder="房卡数量..."/>
                    </div>
                </div>
            </div>


            <input type="hidden" id="imgUrl" name="imgUrl" value="${record.imgUrl}">

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
    <button id="btn" type="button" onclick="webside.common.loadPage('/award/listUi.html<c:if
            test="${!empty record}">?page=${page.pageNum }&rows=${page.pageSize }&sidx=${page.orderByColumn }&sord=${page.orderByType }</c:if>')"
            class="btn btn-info btn-sm">
        <i class="fa fa-undo"></i>&nbsp;返回
    </button>
</div>

<script type="text/javascript">

    $("#remark").change(function(){
        var opt = $("#remark").val();
        if (opt == '1') {
            $('#descrLabel').css('display','block');
            $('#descrDiv').css('display','block');
        }else {
            $('#descrLabel').css('display','none');
            $('#descrDiv').css('display','none');
            $('#descr').val('');
        }
    });

    var opt = $("#remark").val();
    if (opt == '1') {
        $('#descrLabel').css('display','block');
        $('#descrDiv').css('display','block');
    }else {
        $('#descrLabel').css('display','none');
        $('#descrDiv').css('display','none');
        $('#descr').val('');
    }

    $(function () {
        $('#storeForm').validate({
            errorElement: 'div',
            errorClass: 'help-block',
            focusInvalid: false,
            ignore: "",
            rules: {
                name: {
                    required: true
                },
                type: {
                    required: true
                },
                integral: {
                    required: true
                },
                awardNum: {
                    required: true
                },
                review: {
                    required: true
                },
                remark: {
                    required: true
                }
            },
            messages: {
                name: "商品名称不能为空！",
                integral: "所需积分不能为空！",
                awardNum: "奖品数量不能为空!",
                review: "请选择是否需要审核！",
                type: "活动类型不能为空!",
                remark:"请选择商品类型"
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
                var remark = $('#remark').val();
                if (remark == '1') {
                    var  descr = $('#descr').val();
                    if (descr == undefined || descr == '') {
                        layer.msg("请输入房卡数量!", {
                            icon : 5
                        });
                        return;
                    }
                }
                var id = $("#id").val();
                var url = "";
                if (id != undefined) {
                    url = '/award/edit.html';
                } else {
                    url = '/award/add.html';
                }
                commit('storeForm', url, '/award/listUi.html');
                //webside.common.commit('storeForm', url, '/acti/listUi.html');
            }
        });

        function commit(formId, commitUrl, jumpUrl) {
            //组装表单数据
            var index;
            var data = $("#" + formId).serialize();
            if (undefined != $("#pageNum").val()) {
                jumpUrl = jumpUrl + '?page=' + $("#pageNum").val() + '&rows=' + $("#pageSize").val() + '&sidx=' + $("#orderByColumn").val() + '&sord=' + $("#orderByType").val();
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
    });

    function uploadImg(img) {// btnImgUpload   actImgUpload
        var	oFile = document.getElementById(img).files[0];
        if (oFile == undefined || oFile == "") {
            layer.msg("请选择上传的文件!", {icon: 5});
            return;
        }
        var fd = new FormData();
        fd.append("file", oFile);
        fd.append("fileName",  oFile.name || oFile.fileName);
        fd.append("fileSize",  oFile.size || oFile.fileSize);
        $.ajax({
            type: "POST",
            contentType:false, //必须false才会避开jQuery对 formdata 的默认处理 , XMLHttpRequest会对 formdata 进行正确的处理
            processData: false, //必须false才会自动加上正确的Content-Type
            url: "${imgUploadUrl}img/upload.action",
            data: fd,
            success: function (data) {
                if (data.errorType == true) {
                    layer.msg("请选择图片类型！", {
                        icon : 5
                    });
                    return;
                }
                if (data.state == 200) {//上传成功,更新用户信息
                    layer.msg("上传成功", {
                        icon : 1
                    });
                    $('#imgUrl').val(data.url);
                    oFile = "";
                }else{
                    layer.msg("上传失败", {
                        icon : 5
                    });
                    oFile = "";
                }
            },
            error:function () {
                oFile = "";
            }
        });
    }

</script>