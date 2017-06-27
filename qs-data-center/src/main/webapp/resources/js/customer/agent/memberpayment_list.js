$(function () {
    jeDate({
        dateCell: '#startDate',
        isinitVal:new Date(),
        //isinitVal:false,
        format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
        minDate: '1900-06-01', //最小日期
        maxDate: '2050-06-01' //最大日期
    });
    jeDate({
        dateCell: '#endDate',
        isinitVal:new Date(),
        //isinitVal:false,
        format: 'YYYY-MM-DD', // 分隔符可以任意定义，该例子表示只显示年月
        minDate: '1900-06-01', //最小日期
        maxDate: '2050-06-01' //最大日期
    });
});

var startDate = $("#startDate").val();
var endDate = $("#endDate").val();

var saveDate = new Date(Date.parse(endDate.replace(/-/g, "/")));
saveDate.setDate(saveDate.getDate()-6);

var year = saveDate.getFullYear();
var month = saveDate.getMonth() + 1 < 10 ? "0" + (saveDate.getMonth() + 1) : saveDate.getMonth() + 1;
var date = saveDate.getDate() < 10 ? "0" + saveDate.getDate() : saveDate.getDate();

startDate = year + "-" + month + "-" + date;

$('#startDate').val(startDate);


var dtGridColumns = [{
    id : 'ptimeStr',
    title : '日期',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header'
},{
    id : 'pamount',
    title : '充值总金额',
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
	    check : true,
	    checkWidth :'37px',
	    extraWidth : '37px',
	    loadURL : sys.rootPath + '/memberpayment/queryMemberpaymentList.html',
	    columns : dtGridColumns,
	    gridContainer : 'dtGridContainer',
	    toolbarContainer : 'dtGridToolBarContainer',
	    tools : 'refresh|export[excel]',
	    exportFileName : '每日充值统计',
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
    
    grid.parameters = new Object();
    grid.parameters['startDate'] = startDate;
    grid.parameters['endDate'] = endDate;
    grid.load();
    queryPayCountTotals(startDate,endDate);
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
    var startDateStr = $("#startDate").val();
    var endDateStr = $("#endDate").val();
    grid.parameters['startDate'] = startDateStr;
    grid.parameters['endDate'] = endDateStr;
     startDate = startDateStr ;
     endDate = endDateStr;
    grid.refresh(true);
    queryPayCountTotals(startDate,endDate);
}


function queryPayCountTotals(startDate,endDate){
	$.ajax({
		type: "POST",
		url: sys.rootPath+'/memberpayment/queryPayAmountTotal.html',
		data:{'startDate':startDate,'endDate':endDate},
		dataType: "json",
		success: function(data){
				$("#countTotals").html(data);
		}
	});
}

