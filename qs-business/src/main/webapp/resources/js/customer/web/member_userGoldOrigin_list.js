var dtGridColumns = [{
    id : 'date',
    title : '日 期',
    type : 'number',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution: function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id : 'type',
    title : '类 型',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return handleInitResponse.execute(value);
    }
},{
    id : 'gold',
    title : '金币/房卡动态',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id : 'nowgold',
    title : '现存金币/房卡',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id : 'title',
    title : '游戏类型',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        if (value == 5) {
            return '<span class="label label-info arrowed-right arrowed-in">麻将</span>'
        }
        return '<span class="label label-success arrowed-in arrowed-in-right">'+value+'</span>';
    }
},{
    id : 'formid',
    title : '代开房',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
},{
    id : 'remark',
    title : '备注',
    type : 'string',
    columnClass : 'text-center',
    headerClass : 'dlshouwen-grid-header',
    resolution:function (value, record, column, grid, dataNo, columnNo) {
        return value;
    }
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
    loadURL : sys.rootPath + '/business/user/goldOrigin.html',
    columns : dtGridColumns,
    gridContainer : 'dtGridContainer',
    toolbarContainer : 'dtGridToolBarContainer',
    tools : 'refresh',
    exportFileName : '用户信息列表',
    pageSize : pageSize,
    pageSizeLimit : [10, 20, 30]
};

var grid = $.fn.dlshouwen.grid.init(dtGridOption);
$(function() {
    if(null != $("#orderByColumn").val() && '' != $("#orderByColumn").val()) {
        grid.sortParameter.columnId = $("#orderByColumn").val();
        grid.sortParameter.sortType = $("#orderByType").val();
    }
    grid.parameters = new Object();
    grid.parameters['mid'] = $('#midSearch').val();

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
    grid.parameters['mid'] = $('#midSearch').val();
    grid.refresh(true);
}

var handleInitResponse = {
    execute:function (data) {
        return eval('handleInitResponse.handle' + data + "()");
    },
    handle0:function () {//
        return "";
    },
    handle1:function () {//
        return "充值购买";
    },
    handle2:function () {//
        return "每日登陆";
    },
    handle3:function () {//
        return "VIP每日登陆";
    },
    handle4:function () {//
        return "生成房间消耗";
    },
    handle5:function () {//
        return "退还房间费用";
    },
    handle6:function () {//
        return "绑定奖励";
    },
    handle7:function () {//
        return "后台添加";
    },
    handle8:function () {//
        return "初始赠送";
    },
    handle9:function () {//
        return "比赛金币消耗";
    },
    handle10:function () {//
        return "比赛金币退还";
    },
    handle11:function () {//
        return "比赛金币奖励";
    },
    handle12:function () {//
        return "开服充值赠送";
    },
    handle13:function () {//
        return "商务添加";
    },
    handle14:function () {//
        return "充值大特惠";
    },
    handle15:function () {//
        return "更新补偿";
    },
    handle16:function () {//
        return "邮件赠送";
    },
    handle17:function () {//
        return "成就奖励";
    }
};

//memberwin.html
//$aLang['gs1'] = '充值购买';
//$aLang['gs2'] = '每日登陆';
//$aLang['gs3'] = 'VIP每日登陆';
//$aLang['gs4'] = '生成房间消耗';
//$aLang['gs5'] = '退还房间费用';
//$aLang['gs6'] = '绑定奖励';
//$aLang['gs7'] = '后台添加';
//$aLang['gs8'] = '初始赠送';
//$aLang['gs9'] = '比赛金币消耗';
//$aLang['gs10'] = '比赛金币退还';
//$aLang['gs11'] = '比赛金币奖励';
//$aLang['gs12'] = '开服充值赠送 ';
//$aLang['gs13'] = '商务添加';
//$aLang['gs14'] = '充值大特惠';
//$aLang['gs15'] = '更新补偿';
//$aLang['gs16'] = '邮件赠送';
//$aLang['gs17'] = '进入房间消费(斗牛)';
//$aLang['gs18'] = '退多扣房费(斗牛)';
//$aLang['gs19'] = '成就奖励';
// $aLang['gs20'] = '';
// $aLang['gs21'] = '老虎机获奖';
// $aLang['gs22'] = '赠送VIP获得';
// $aLang['gs23'] = '召回';
// $aLang['gs24'] = '积分兑换实物';
// $aLang['gs25'] = '破产补助';
// $aLang['gs26'] = '金币兑换平台币';
// $aLang['gs27'] = '平台币兑换金币';
// $aLang['gs28'] = '消费金币卡送金币';
// $aLang['gs29'] = '黄钻每日';
// $aLang['gs30'] = '黄钻新手礼包';
// $aLang['gs31'] = '银行转帐抽水';
//$aLang['gs32'] = '';
// $aLang['gs33'] = '大场玩牌抽奖';
// $aLang['gs34'] = '添加到QQ面板';
// $aLang['gs35'] = '移动红包';
// $aLang['gs36'] = '来源地购买';
// $aLang['gs37'] = '十连抽';
// $aLang['gs38'] = '十连抽排行榜';
// $aLang['gs39'] = '十连抽消耗';
// $aLang['gs40'] = '老虎机消耗';
// $aLang['gs41'] = 'boss场送金币';
// $aLang['gs42'] = '称号送金币';
// $aLang['gs43'] = '积分换金币';
// $aLang['gs44'] = '玩牌抽iphone';
// $aLang['gs45'] = '大师场用户召回';
// $aLang['gs46'] = '更名';
// $aLang['gs47'] = 'VIP抽奖';
// $aLang['gs48'] = '升级奖励';
// $aLang['gs49'] = '送红包';
// $aLang['gs50'] = '领 红包';
// $aLang['gs51'] = '红包退还';
// $aLang['gs52'] = '微信红包';
// $aLang['gs53'] = '系统发放红包';
// $aLang['gs54'] = '卡牌兑换';
// $aLang['gs55'] = '买卡牌';
// $aLang['gs56'] = '摇骰子得';
// $aLang['gs57'] = '宝箱得金币';
// $aLang['gs58'] = '骰子重置';
// $aLang['gs59'] = '邮件获得';
// $aLang['gs60'] = '秒杀夺宝消耗';
// $aLang['gs61'] = '秒杀夺宝获得';
// $aLang['gs62'] = '老虎机奖励';
// $aLang['gs63'] = '坐下';
// $aLang['gs64'] = '站起';
// $aLang['gs65'] = '重新携带';
// $aLang['gs66'] = '打牌输赢';
// $aLang['gs67'] = '招募奖励';
// $aLang['gs68'] = '签到奖励';
// $aLang['gs69'] = '补签';
// $aLang['gs70'] = '打赏荷官';
// $aLang['gs71'] = '充值活动赠送';








