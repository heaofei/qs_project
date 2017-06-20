<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"/>

<c:choose>
	<c:when test="${gameType <20}">
<script type="text/javascript"
        src="${ctx }/resources/js/customer/web/team_selectVipDirectlyPoxy_list.js"></script>
	</c:when>
	<c:otherwise>
<script type="text/javascript"
        src="${ctx }/resources/js/customer/web/team_selectVipDirectlyPoxy_list2.js"></script>
	</c:otherwise>
</c:choose>

<script src="${ctx}/resources/js/customer/index/index_list.js" type="text/javascript"></script>
<div class="page-content" style="padding-top: 20px">
    <div class="controls controls-row">
        <div class="controls controls-row">
        <form action="">
				<div class="col-sm-3">
					<div class="input-group" style="width: 100%;">
						<input class="form-control" name="midSearch" id="midSearch"
							type="text" value="" placeholder="代理商ID..." /> <span
							class="input-group-btn">
							<button id="btnSearch" class="btn btn-primary btn-sm"
								type="button">
								<i class="fa fa-search"></i>查询
							</button>
						</span>
					</div>
				</div>
				<!-- 	  
            <div class="col-sm-2">
                <div class="clearfix">
                    <input class="form-control" name="midSearch" id="midSearch" type="text"
                           value="" placeholder="输入用户id..."/>
                           <br>
		            <button id="btnSearch" class="btn btn-primary btn-sm" type="button">
		                <i class="fa fa-search"></i>查看
		            </button>
                </div>
            </div> -->
            </form>
        </div>
    </div>

    <div class="row" style="margin-top:5px;">
        <div class="col-xs-12 widget-container-col ui-sortable"
             style="min-height: 127px;">
            <!-- #section:custom/widget-box.options.transparent -->
            <div class="widget-box transparent ui-sortable-handle"
                 style="opacity: 1; z-index: 0;">
                <div class="widget-header">
                    <h4 class="widget-title lighter">下级代理商查询</h4>
                    <div class="widget-toolbar no-border">
                        <a href="#" data-action="fullscreen" class="orange2">
                            <i class="ace-icon fa fa-arrows-alt"></i>
                        </a>
                        <a href="#" data-action="collapse" class="green">
                            <i class="ace-icon fa fa-chevron-up"></i>
                        </a>
                    </div>
                </div>
				<%-- <c:choose>
					<c:when test="${type ==1}">
						<jsp:include
							page="${cxt }/WEB-INF/view/web/agent/query/selectVipPoxy.jsp"
							flush="true" />
					</c:when>
					<c:otherwise>
						<jsp:include
							page="${cxt }/WEB-INF/view/web/agent/query/selectVipUser.jsp"
							flush="true" />
					</c:otherwise>
				</c:choose> --%>
				<div class="widget-body" style="display: block;">
                    <div class="widget-main padding-6 no-padding-left no-padding-right">
                        <input id="mid" type="hidden" value="${id}">
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
<script>

function selectGlod(id){
	  layer.open({
	        type: 2,
	        title:"金币余额",
	        area: ['80%','40%'],
	        fixed: false, //不固定
	        maxmin: true,
	        content: sys.rootPath + '/agentroom/getGoldCountUi.html?id='+id
	}); 
}
//查看明细
	function showSettleDetail(mid){
  
	  layer.open({
	        type: 2,
	        title:mid+"-充值明细",
	        area: ['95%','90%'],
	        fixed: false, //不固定
	        maxmin: true,
	        content: sys.rootPath + '/agentroom/selectVipDirectlyInfoUi.html?mid='+mid
	});
}


//代理开房

var openRoom=function (mid,type){
	   
var url = "";

	if (type == 1) {
		url = "${ctx}/agentroom/insertOpenRoom.html";
	} else {
		url = "${ctx}/agentroom/deleteOpenRoom.html";
	}
	
	$.ajax({
		type : "POST",
		url : url,
		data : {
			"mid" : mid
		},
		dataType : "json",
		success : function(msg) {
			if (msg.success == true) {
				grid.parameters = new Object();

				grid.parameters['type'] = $('input:radio:checked').val();
				grid.parameters['querymid'] = $("#midSearch").val();
				grid.refresh(true);
			} else {
				grid.parameters = new Object();

				grid.parameters['type'] = $('input:radio:checked').val();
				grid.parameters['querymid'] = $("#midSearch").val();
				grid.refresh(true);
			}
		}

	});
}
</script>