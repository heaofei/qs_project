$(function () {
    jeDate({
        dateCell: '#stime',
        isinitVal:new Date(),
        //isinitVal:false,
        format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
        minDate: '1900-06-01', //最小日期
        maxDate: '2050-06-01' //最大日期
    });
    jeDate({
        dateCell: '#etime',
        isinitVal:new Date(),
        //isinitVal:false,
        format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
        minDate: '1900-06-01', //最小日期
        maxDate: '2050-06-01' //最大日期
    });
});
var stime =  $("#stime").val();
var etime =  $("#etime").val();
var gameType = $('#gameType').val();
var dtGridColumns = [{
    id : 'appName',
    title : '游戏名称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'playName',
    title : '玩法名称',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'totals',
    title : '创建房间数',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : '',
    title : '操作',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        
        return '<a href="javascript:lookCreateRoomDetail('+record.appId+',\''+record.playId+'\')">查看明细</a>';
    }
}];

//动态设置jqGrid的rowNum
var pageSize = $("#pageSize").val();
pageSize = pageSize == 0 || pageSize == "" ? sys.pageNum : pageSize;


var dtGridOption = {
	    lang : 'zh-cn',
	    ajaxLoad : true,
	    check : true,
	    checkWidth :'37px',
	    extraWidth : '37px',
	    loadURL : sys.rootPath + '/createRoom/createRoom.html',
	    columns : dtGridColumns,
	    gridContainer : 'dtGridContainer',
	    toolbarContainer : 'dtGridToolBarContainer',
	    tools : 'refresh|export[excel]',
	    exportFileName : '用户创建房间统计',
	    //tools : 'refresh',
	    pageSize : pageSize,
	    pageSizeLimit : [10, 20, 30]
	};

var grid = $.fn.dlshouwen.grid.init(dtGridOption);


//使用刚指定的配置项和数据显示图表。
//myChart.setOption(option);
/*var setOption=function (appName,playName,stime,etime){
	$.ajax({
		type: "POST",
		url: sys.rootPath+'/createRoom/createRoomCount.html',
		data:{'stime':stime,'etime':etime,'appName':appName,'playName':playName},
		dataType: "json",
		success: function(data){
			debugger;
			var array = data.split("@");
			option.series[0].data=JSON.parse(array[1]);
			option.xAxis.data= JSON.parse(array[0]);
			// 使用刚指定的配置项和数据显示图表。
			myChart.setOption(option);
			console.log(array[1]);
			console.log(array[0]);
		}
	});
}*/

$(function() {
    if(null != $("#orderByColumn").val() && '' != $("#orderByColumn").val())
    {
        grid.sortParameter.columnId = $("#orderByColumn").val();
        grid.sortParameter.sortType = $("#orderByType").val();
    }
    	grid.parameters = new Object();
    	grid.parameters['stime'] = stime;
    	grid.parameters['etime'] = etime;
    	grid.parameters['groupId'] = $("#groupIdBusiness").val();
	    grid.parameters['businessId'] = $("#businessIdByGroupId").val();
	    grid.load();
    //setOption(null,null,stime,etime);
    queryCountTotals(stime,etime,null,null);
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
	var appName = $("#appName").val();
	var playName = $("#playName").val();
	var groupId = $("#groupIdBusiness").val();
	var businessId = $("#businessIdByGroupId").val();
		 stime = $("#stime").val();
		 etime = $("#etime").val();
	    grid.parameters = new Object();
	    grid.parameters['appName'] = appName;
	    grid.parameters['playName'] = playName;
    	grid.parameters['stime'] = stime;
    	grid.parameters['etime'] = etime;
    	grid.parameters['groupId'] = groupId;
	    grid.parameters['businessId'] = businessId;
    //setOption(appName,playName,stime,etime);
    queryCountTotals(stime,etime,appName,playName,groupId,businessId);
    grid.refresh(true);
}



/*//基于准备好的dom，初始化echarts实例
var myChart = echarts.init(document.getElementById('main'));

//指定图表的配置项和数据
var option = {
 title: {
     text: '创建房间数'
 },
 tooltip: {},
 legend: {
     data:['房间数']
 },
 xAxis: {
     data: []
 },
 yAxis: {},
 series: [{
     name: '房间数',
     //color:'red',
     type: 'line',
     data: []
 }]
}; */

var lookCreateRoomDetail = function(appId,playId){
	 var st  = $("#stime").val();
	 var et  = $("#etime").val();
	 var groupId = $("#groupIdBusiness").val();
	 var businessId = $("#businessIdByGroupId").val();
	 var playName = $("#playName").val();
	webside.common.loadPage('/createRoom/toCreateRoomSecondDetailsUi.html?appId='+appId+'&playId='+playId+'&stime='+st+'&etime='+et+'&groupId='+groupId+'&businessId='+businessId)
}

function queryCountTotals(stime,etime,appName,playName,groupId,businessId){
	$.ajax({
		type: "POST",
		url: sys.rootPath+'/createRoom/queryCreateRoomCountTotal.html',
		data:{'stime':stime,'etime':etime,'appName':appName,'playName':playName,'groupId':groupId,'businessId':businessId},
		dataType: "json",
		success: function(data){
				$("#countTotals").html(data);
		}
	});
}

