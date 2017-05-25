<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath }" />
<script type="text/javascript" src="${ctx }/resources/js/log/user/create_room_list.js"></script>

<style>
.col-sm-1{
width:80px;
}
</style>

<!-- 头部 -->
<!-- <div class="page-header">
	  <button id="btnAdd" type="button" onclick="webside.common.addModel('/dict/toAdd.html')" class="btn btn-primary btn-sm">
	  	<i class="fa fa-user-plus"></i>&nbsp;添加
	 </button>
	 <button id="btnEdit" type="button" onclick="webside.common.editModel('/dict/editUI.html')" class="btn btn-info btn-sm">
		 <i class="fa fa-pencil-square-o"></i>&nbsp;编辑
	</button>
	 <button id="btnEdit" type="button" onclick="webside.common.editModel('/dict/viewUI.html')" class="btn btn-info btn-sm">
		 <i class="fa fa-pencil-square-o"></i>&nbsp;查看
	</button>
	
</div> -->

<!-- 搜索框 -->
     <form class="form-horizontal" >
				<div class="form-group" >
		           	<label class="control-label col-sm-1 no-padding-right">游戏名称</label>
	                <div class="col-sm-2">
	                    <input class="form-control" name="appName" id="appName" type="text"
	                               placeholder="游戏名称..."/>
	                </div>
		           	<label class="control-label col-sm-1 no-padding-right">玩法名称</label>
	                <div class="col-sm-2">
	                    <input class="form-control" name="playName" id="playName" type="text"
	                               placeholder="游戏玩法..."/>
	                </div>
		           	<label class="control-label col-sm-1 no-padding-right">开始时间</label>
	                <div class="col-sm-2">
	                    <input class="form-control" name="stime" id="stime" type="text"
	                               placeholder="开始时间..."/>
	                </div>
		           	<label class="control-label col-sm-1 no-padding-right">结束时间</label>
	                <div class="col-sm-2">
	                    <input class="form-control" name="etime" id="etime" type="text"
	                               placeholder="结束时间..."/>
	                </div>
	                <div class="col-sm-1">
	                    <button id="btnSearch" class="btn btn-primary btn-sm" type="button">
	               			 <i class="fa fa-search"></i>查询
	           			</button>
	                </div>
				</div>
	</form>

<input type="hidden" id="gameType" value="${gameType}"/>

<!-- 为 ECharts 准备一个具备大小（宽高）的 DOM -->
<div id="main" class="col-xs-11" style="width:100%;min-height: 300px;"></div>

<div class="row" style="margin-top:5px;">
	<div class="col-xs-12 widget-container-col ui-sortable"
		style="min-height: 127px;">
		<div class="widget-box transparent ui-sortable-handle"
			style="opacity: 1; z-index: 0;">
			<div class="widget-header">
				<h4 class="widget-title lighter">创建房间数据统计列表</h4>
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
