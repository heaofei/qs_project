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
    id : 'name',
    title : '活动名称',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'type',
    title : '类型',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'noticetime',
    title : '活动通知时间',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'starttime',
    title : '活动预告开始时间',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'endtime',
    title : '活动预告结束时间',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'content',
    title : '活动内容',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'copyright',
    title : '版权所有',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'remark',
    title : '备注',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
}, {
    id : 'desr',
    title : '描述',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'id',
    title : '操作',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    hideType : 'xs',
    resolution : function(value, record, column, grid, dataNo, columnNo) {
        return '<a href="#" style="cursor: pointer;" onclick="edit(\''+record.id+'\')">编辑</a>';
    }
}];

function edit(id){
	webside.common.loadPage('/cfg/actiTime/editUI.html?id='+id)
  }

//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;

var dtGridOption = {
    lang : 'zh-cn',
    ajaxLoad : true,
    check : false,
    checkWidth :'37px',
    extraWidth : '37px',
    loadURL : sys.rootPath + '/cfg/actiTime/list.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh',
    exportFileName : '活动预告时间管理',
    pageSize : pageSize,
    pageSizeLimit : [10, 20, 30]
};

var grid = $.fn.dlshouwen.grid.init(dtGridOption);
$(function() {
    if(null != $("#orderByColumn").val() && '' != $("#orderByColumn").val())
    {
        grid.sortParameter.columnId = $("#orderByColumn").val();
        grid.sortParameter.sortType = $("#orderByType").val();
    }
    grid.load();
    $("#btnSearch").click(customSearch);
    
    //注册回车键事件
    document.onkeypress = function(e){
    var ev = document.all ? window.event : e;
        if(ev.keyCode==13) {
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
    grid.parameters['name'] = $("#searchKey").val();
    grid.refresh(true);
}


