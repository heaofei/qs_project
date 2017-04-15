<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<script type="text/javascript" src="${pageContext.request.contextPath }/resources/js/customer/web/ip_list.js"></script>

<div class="page-header">
	  <button id="btnAdd" type="button" onclick="webside.common.addModel('/game/ip/addUI.html')" class="btn btn-primary btn-sm">
	  	<i class="fa fa-user-plus"></i>&nbsp;添加
	 </button>
	<button id="showHistory" type="button" onclick="showHistory()" class="btn btn-success btn-sm">
	<i class="fa fa-user-md"></i>&nbsp;查看日志
	</button>
	<button id="showHistoryFixRecord" type="button" onclick="showHistoryFixRecord()" class="btn btn-danger btn-sm">
		<i class="fa fa-user-md"></i>&nbsp;查看历史修改记录
	</button>
</div>

<div class="input-group">
     <input id="searchKey" type="text" class="input form-control" placeholder="ip列表...">
     <span class="input-group-btn">
         <button id="btnSearch" class="btn btn-primary btn-sm" type="button"> <i class="fa fa-search"></i> 搜索</button>
     </span>
</div>

<div class="row" style="margin-top:5px;">
	<div class="col-xs-12 widget-container-col ui-sortable"
		style="min-height: 127px;">
		<!-- #section:custom/widget-box.options.transparent -->
		<div class="widget-box transparent ui-sortable-handle"
			style="opacity: 1; z-index: 0;">
			<div class="widget-header">
				<h4 class="widget-title lighter">服务器入口列表</h4>
				<div class="widget-toolbar no-border">
					<a href="#" data-action="fullscreen" class="orange2"> 
						<i class="ace-icon fa fa-arrows-alt"></i>
					</a> 
					<a href="#" data-action="collapse" class="green"> 
						<i class="ace-icon fa fa-chevron-up"></i>
					</a>
				</div>
			</div>

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

<script>

    function showHistory() {
        layer.open({
            type: 2,
            title:"查看日志",
            area: ['95%','90%'],
            fixed: false, //不固定
            maxmin: true,
            content: sys.rootPath + '/ipAddressUserLog/listUi.html'
        });
    }

    function showHistoryFixRecord() {
        layer.open({
            type: 2,
            title:"查看历史修改记录",
            area: ['95%','90%'],
            fixed: false, //不固定
            maxmin: true,
            content: sys.rootPath + '/ipAddressLog/listUi.html'
        });
    }

</script>
