package com.qs.webside.query.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.util.PageUtil;
import com.qs.log.game.service.IPlayerRecordService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * //@Author:zun.wei, @Date:2017/4/17 11:16
 * 牌局查询
 * Created by zun.wei on 2017/4/17.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Controller
@RequestMapping(value = "/query/")
public class CardRecordController extends BaseController {

    //   /query/cardRecordUi.html

    @Resource
    private IPlayerRecordService playerRecordService;

    @RequestMapping(value = "cardRecordUi.html", method = RequestMethod.GET)
    public String cardRecordUi(Model model, HttpServletRequest request) {
        PageUtil page = super.getPage(request);
        model.addAttribute("page", page);
        return "WEB-INF/view/web/query/cardRecord_query_list";
    }

    /**
     * //@Author:zun.wei, @Date:2017/4/17 19:14
     * 订单明细
     * @param gridPager
     * @return
     * @throws Exception
     */
    @RequestMapping("cardRecord.html")
    @ResponseBody
    public Object cardRecord(String gridPager) throws Exception {
        Map<String, Object> parameters = null;
        // 映射Pager对象
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        // 判断是否包含自定义参数
        parameters = pager.getParameters();
        if (parameters.size() < 0) {
            parameters.put("name", null);
        }
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        List<Map<String,Object>> list = playerRecordService.queryCardRecordByPage(parameters);
        return getReturnPage(pager, page, list);
    }


}
