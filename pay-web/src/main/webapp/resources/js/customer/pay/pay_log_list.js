var dtGridColumns = [{
    id : 'mid',
    title : '邀请者MID',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return value ;
    }
}, {
    id : 'addDate',
    title : '添加日期',
    type : 'date',
    format : 'yyyy-MM-dd',
    otype : 'string',
    oformat : 'yyyy-MM-dd',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs|sm|md'
},{
    id : 'rebatetotal',
    title : '返现',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id : 'gameType',
    title : '游戏类型',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
    	
    	var x="";
    	switch (value)
    	{
    	case 1:
    		x="牵手跑得快";
    		break;
    	case 2:
    		x="牵手跑胡子";
    		break;
    	case 3:
    		x="疯狂斗牛OL";
    		break;
    	case 4:
    		x="牵手湖南麻将";	
    		break;
    	}
        return x ;
    }
},{
    id : 'status',
    title : '支付状态',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
    	if (value==0){
    		value='支付失败';
    	}
    	if (value==1){
    		value='支付成功';
    	}
        return value ;
    }
}, {
    id : 'cCreateTime',
    title : '创建时间',
    type : 'date',
    format : 'yyyy-MM-dd hh:mm:ss',
    otype : 'string',
    oformat : 'yyyy-MM-dd hh:mm:ss',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs|sm|md'
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
    loadURL : sys.rootPath + '/enterprise/payLogUi.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh',
    exportFileName : '支付日志列表',
    pageSize : pageSize,
    pageSizeLimit : [10, 20, 30]
};

var grid = $.fn.dlshouwen.grid.init(dtGridOption);
$(function() {
	
	if (null != $("#orderByColumn").val() && '' != $("#orderByColumn").val()) {
		grid.sortParameter.columnId = $("#orderByColumn").val();
		grid.sortParameter.sortType = $("#orderByType").val();
	}
	grid.parameters = new Object();
    grid.load();
    grid.parameters['id'] = $("#id").val();
    grid.parameters['mid'] = $("#mid").val();
    grid.parameters['startTime'] = $("#startTime").val();
    grid.parameters['endTime'] = $("#endTime").val();
    grid.parameters['rebatetotal'] = $("#rebatetotal").val();
    grid.parameters['gameType'] = $("#gameType :selected").val();
    grid.parameters['status'] = $("#status :selected").val();
    $("#btnSearch").click(customSearch);
    //注册回车键事件
    document.onkeypress = function (e) {
        var ev = document.all ? window.event : e;
        if (ev.keyCode == 13) {
            customSearch();
        }
    };


});

/**
 * 自定义查询
 * 这里不传入分页信息，防止删除记录后重新计算的页码比当前页码小而导致计算异常
 */
function customSearch() {
    grid.parameters = new Object();
    var data = $("#formId").serialize();
    
    //{data=id=&mid=&startTime=2017-04-05&endTime=2017-04-05&rebatetotal=&gameType=0&status=0}
    grid.parameters['id'] = $("#id").val();
    grid.parameters['mid'] = $("#mid").val();
    grid.parameters['startTime'] = $("#startTime").val();
    grid.parameters['endTime'] = $("#endTime").val();
    grid.parameters['rebatetotal'] = $("#rebatetotal").val();
    grid.parameters['gameType'] = $("#gameType :selected").val();
    grid.parameters['status'] = $("#status :selected").val();
    grid.refresh(true);
}

