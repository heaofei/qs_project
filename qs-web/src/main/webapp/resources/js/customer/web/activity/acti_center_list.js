var dtGridColumns = [
{
    id: 'id',
    title: '序号',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return dataNo + 1;
    }
}, {
    id: 'title',
    title: '活动标题',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
},/*, {
    id: 'descr',
    title: '描述',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
}, */{
    id: 'type',
    title: '活动类型',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
},{
    id: 'status',
    title: '是否启用',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        if(value =='0'){
            return '<span class="label label-sm label-info arrowed arrowed-in-right">已启用</span>';
        }else  if(value =='1'){
            return '<span class="label label-sm label-info arrowed arrowed-righ" style="color: red;">已禁用</span>';
        }
    }
},{
    id: 'sort',
    title: '排序',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
},{
    id: 'btnUrl',
    title: '按钮url',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return '<img src="'+value+'" style="width: 50px;height: 50px;"/>';
    }
},{
    id: 'jumpUrl',
    title: '跳转url',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return '<span style="width: 100px;">'+value+'</span>';
    }
},{
    id: 'actImgUrl',
    title: '活动图片url',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        //return '<span style="width: 100px;">'+value+'</span>';
        return '<img src="'+value+'" style="width: 50px;height: 50px;"/>';
    }
},{
    id: 'startTime',
    title: '活动开始时间',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
},{
    id: 'endTime',
    title: '活动结束时间',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
},{
    id: 'periodStartTime',
    title: '活动开始时段',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
},{
    id: 'periodEndTime',
    title: '活动结束时段',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
},{
    id: 'cardRecord',
    title: '玩牌局数',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
},{
    id: 'reward',
    title: '奖励',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header'
},{
    id: 'id',
    title: '操作',
    type: 'string',
    columnClass: 'text-center',
    headerClass: 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return '<a href="#" style="cursor: pointer;" onclick="edit(\'' + record.id + '\')">编辑</a> ';
            /*+' <a href="#" style="cursor: pointer;" onclick="showUpdateLog(\'' + record.id + '\')">查看更新日志</a>'
            +' <a href="#" style="cursor: pointer;" onclick="updateAPKPak(\'' + record.id + '\')">更新APK包</a>'*/
    }
}];


function edit(id) {
    webside.common.loadPage('/acti/editUI.html?id=' + id)
}

/*function showUpdateLog(id) {
    layer.open({
        type: 2,
        title:'查看更新日志',
        area: ['80%','80%'],
        fixed: false, //不固定
        maxmin: true,
        content: sys.rootPath + '/game/record/listUi.html?id='+id
    });
}

function updateAPKPak(id) {
    layer.open({
        type: 2,
        title:'更新apk包',
        area: ['80%','80%'],
        fixed: false, //不固定
        maxmin: true,
        content: sys.rootPath + '/game/record/upload.html?id='+id
    });
}*/

//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;

var dtGridOption = {
    lang: 'zh-cn',
    ajaxLoad: true,
    check: false,
    checkWidth: '37px',
    extraWidth: '37px',
    loadURL: sys.rootPath + '/acti/list.html',
    columns: dtGridColumns,
    gridContainer: 'dtGridContainer',
    toolbarContainer: 'dtGridToolBarContainer',
    tools: 'refresh',
    exportFileName: '活动中心列表',
    pageSize: pageSize,
    pageSizeLimit: [10, 20, 30]
};

var grid = $.fn.dlshouwen.grid.init(dtGridOption);
$(function () {
    if (null != $("#orderByColumn").val() && '' != $("#orderByColumn").val()) {
        grid.sortParameter.columnId = $("#orderByColumn").val();
        grid.sortParameter.sortType = $("#orderByType").val();
    }
    grid.load();
    $("#btnSearch").click(customSearch);

    //注册回车键事件
    /*document.onkeypress = function (e) {
        var ev = document.all ? window.event : e;
        if (ev.keyCode == 13) {
            customSearch();
        }
    };*/

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

