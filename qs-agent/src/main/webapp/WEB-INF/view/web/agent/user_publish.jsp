<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<%@include file="/common/common.jspf" %>
<script src="${ctx}/resources/js/customer/index/index_list.js" type="text/javascript"></script>
<div class="page-content">
	<!--公告-->
    <div class="well">
             <img class="img-thumbnail publish-img" src="${imgUrl }" alt="公告" title="公告" />
    </div>
     <div class="center">
        <div class="input-group" onclick="closeLayer()">
        <span class="input-group-btn">
        <button class="btn btn-app btn-primary btn-xs" type="button">
            <i class="icon-trash bigger-200"></i>关闭
        </button>
    </span>
        </div>
        <script>
            function closeLayer() {
                var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
                parent.layer.close(index);
            }
        </script>
    </div>
</div>