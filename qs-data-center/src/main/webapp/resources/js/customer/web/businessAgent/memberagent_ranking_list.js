
var gameType="";
//chare数据
$(function(){ 
//表格格数据
    var dtGridColumns = [{
        id : 'rowno',
        title : '序号',
        type : 'number',
        columnClass : 'text-center',
        headerClass : 'dlshouwen-grid-header',
        resolution: function (value, record, column, grid, dataNo, columnNo) {
            return dataNo+1;
        }
    },{
        id : 'rank',
        title : '排名',
        type : 'number',
        columnClass : 'text-center',
        headerClass : 'dlshouwen-grid-header',
        resolution: function (value, record, column, grid, dataNo, columnNo) {
            var str=dataNo + 1;
            return  str;
        }
    },{
        id : 'name',
        title : '商务名称',
        type : 'string',
        columnClass : 'text-center',
        headerClass : 'dlshouwen-grid-header'
    },{
        id : 'realname',
        title : '代理商名称',
        type : 'string',
        columnClass : 'text-center',
        headerClass : 'dlshouwen-grid-header'
    },{
        id : 'date',
        title : '代理商注册时间',
        type : 'string',
        columnClass : 'text-center',
        headerClass : 'dlshouwen-grid-header'
    },{
        id : 'paytotal',
        title : '代理商团队充值总金额',
        type : 'number',
        columnClass : 'text-center',
        headerClass : 'dlshouwen-grid-header'
    },{
        id : 'rebatetotal',
        title : '返利总金额',
        type : 'number',
        columnClass : 'text-center',
        headerClass : 'dlshouwen-grid-header'
    },{
        id : 'bindpeople',
        title : '代理商团队招募总人数',
        type : 'number',
        columnClass : 'text-center',
        headerClass : 'dlshouwen-grid-header'
    },{
        id : 'startDate',
        title : '充值开始时间',
        type : 'number',
        columnClass : 'text-center',
        headerClass : 'dlshouwen-grid-header'
    },{
        id : 'endDate',
        title : '充值结束日期',
        type : 'number',
        columnClass : 'text-center',
        headerClass : 'dlshouwen-grid-header'
    }];

	//动态设置jqGrid的rowNum
	var pageSize = $("#pageSize").val();
	pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;

	var dtGridOption = {
	    lang : 'zh-cn',
	    ajaxLoad : true,
	    check : false,
	    checkWidth :'37px',
	    extraWidth : '37px',
	    loadURL : sys.rootPath + '/weekdown/memberagentRankingList.html',
	    columns : dtGridColumns,
	    gridContainer : 'dtGridContainer',
	    toolbarContainer : 'dtGridToolBarContainer',
	    tools : 'refresh|export[excel]',
	    exportFileName : '星级代理商数量统计-商务星级明细',
	    pageSize : pageSize,
	    pageSizeLimit : [10, 20, 30,50,100]
	};
	var grid = $.fn.dlshouwen.grid.init(dtGridOption);
		if (null != $("#orderByColumn").val() && '' != $("#orderByColumn").val()) {
			grid.sortParameter.columnId = $("#orderByColumn").val();
			grid.sortParameter.sortType = $("#orderByType").val();
		}
		
		grid.parameters = new Object();
		var requirtDate="";
		var searchYear =  $('#searchYear').val();
        var searchDate =  $('#searchDate').val();
        if (searchYear == "请选择年份" || searchDate == "请选择日期") {
        	requirtDate="";
        }else {
        	requirtDate = searchYear + "-" + searchDate;
        }
        //getShotEchars();
	    grid.parameters = new Object();
	    grid.parameters['gameType'] =gameType; 
	    grid.parameters['eDate'] = requirtDate;
	    grid.load();
	   // debugger;
	    //queryTarCountTotals(requirtDate,null,null);
	    $("#btnSearch").click(customSearch);
    	$("#btnExport").click(exportAll);
	    //注册回车键事件
	    document.onkeypress = function (e) {
	        var ev = document.all ? window.event : e;
	        if (ev.keyCode == 13) {
	            customSearch();
	        }
	    };



	/**
	 * 自定义查询
	 * 这里不传入分页信息，防止删除记录后重新计算的页码比当前页码小而导致计算异常
	 */
	function customSearch() {
		var searchYear =  $('#searchYear').val();
        var searchDate =  $('#searchDate').val();
        var requirtDate = "";
        if (searchYear == "请选择年份") {
        	requirtDate="";
        	alert("请选择年份");
        	return;
        }else if(searchDate == "请选择日期"){
        	alert("请选择日期");
        	return;
        }
        else{
        	requirtDate = searchYear + "-" + searchDate;
        }
        var v_groupId = $("#groupIdBusiness").val();
        var v_businessId = $("#businessIdByGroupId").val();
	    grid.parameters = new Object();
	    grid.parameters['gameType'] =gameType; 
	    grid.parameters['eDate'] = requirtDate;
	    grid.parameters['groupId'] = v_groupId;
	    grid.parameters['businessId'] = v_businessId;
	    grid.refresh(true);
	    //queryTarCountTotals(requirtDate,v_groupId,v_businessId);
	    //getShotEchars();
	}


});


function queryTarCountTotals(eDate,groupId,businessId){
	debugger;
	$.ajax({
		type: "POST",
		url: sys.rootPath+'/weekdown/queryStarCountTotals.html',
		data:{'eDate':eDate,'groupId':groupId,'businessId':businessId},
		dataType: "json",
		success: function(data){
				$("#countTotals").html(data);
		}
	});
}

function exportAll() {
    debugger;
    var searchYear =  $('#searchYear').val();
    var searchDate =  $('#searchDate').val();
    var requirtDate = "";
    if (searchYear == "请选择年份") {
        requirtDate="";
        alert("请选择年份");
        return;
    }else if(searchDate == "请选择日期"){
        alert("请选择日期");
        return;
    }
    else{
        requirtDate = searchYear + "-" + searchDate;
    }

    var saveDate = new Date(Date.parse(requirtDate.replace(/-/g, "/")));
    saveDate.setDate(saveDate.getDate()-6);

    var year = saveDate.getFullYear();
    var month = saveDate.getMonth() + 1 < 10 ? "0" + (saveDate.getMonth() + 1) : saveDate.getMonth() + 1;
    var date = saveDate.getDate() < 10 ? "0" + saveDate.getDate() : saveDate.getDate();

    var startDate = year + "-" + month + "-" + date;

	$("#startDate").val(startDate);
	$("#endDate").val(requirtDate);
	debugger;
    $("#exportAction").submit();
}
