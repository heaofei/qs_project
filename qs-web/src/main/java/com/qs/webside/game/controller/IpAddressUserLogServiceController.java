package com.qs.webside.game.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qs.common.base.basecontroller.BaseController;
import com.qs.common.dtgrid.model.Pager;
import com.qs.common.exception.SystemException;
import com.qs.common.util.PageUtil;
import com.qs.webside.game.model.IpaddressUseLog;
import com.qs.webside.game.service.IIpAddressUserLogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 查看日志---服务器入口分配日志
 * Created by zun.wei on 2017/4/13.
 * To change this template use File|Default Setting
 * |Editor|File and Code Templates|Includes|File Header
 */
@Controller
@RequestMapping(value = "/ipAddressUserLog/")
public class IpAddressUserLogServiceController extends BaseController {

    @Resource
    private IIpAddressUserLogService iIpAddressUserLogService;

    @RequestMapping(value = "listUi.html", method = RequestMethod.GET)
    public String listUi(Model model, String id, HttpServletRequest request) {
        try {
            PageUtil page = super.getPage(request);
            model.addAttribute("page", page);
            model.addAttribute("id", id);
            return "/WEB-INF/view/web/ip/ip_userLog_list";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    @RequestMapping("list.html")
    @ResponseBody
    public Object list(String gridPager) throws Exception {
        Map<String, Object> parameters = null;
        // 映射Pager对象
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        // 判断是否包含自定义参数
        parameters = pager.getParameters();
        //System.out.println("parameters = " + parameters.get("apkId"));
        if (parameters.size() < 0) {
            //parameters.put("site", null);
        }
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        List<IpaddressUseLog> list = iIpAddressUserLogService.queryListByPage(parameters);
        return getReturnPage(pager, page, list);
    }


    /**
     * @Author:zun.wei , @Date:2017/5/12 10:17
     * @Description:根据级别统计登录次数入口
     * @param model
     * @param request
     * @return
     */
    @RequestMapping(value = "listByTypeUi.html", method = RequestMethod.GET)
    public String listByTypeUi(Model model, HttpServletRequest request) {
        try {
            PageUtil page = super.getPage(request);
            model.addAttribute("page", page);
            return "/WEB-INF/view/web/ip/ip_queryByType_list";
        } catch (Exception e) {
            throw new SystemException(e);
        }
    }

    /**
     * @Author:zun.wei , @Date:2017/5/12 10:18
     * @Description:根据级别统计登录次数
     * @param gridPager
     * @return
     * @throws Exception
     */
    @RequestMapping("listByType.html")
    @ResponseBody
    public Object listByType(String gridPager) throws Exception {
        Map<String, Object> parameters = null;
        // 映射Pager对象
        Pager pager = JSON.parseObject(gridPager, Pager.class);
        // 判断是否包含自定义参数
        parameters = pager.getParameters();
        //System.out.println("parameters = " + parameters.get("apkId"));
        if (parameters.size() < 0) {
            //parameters.put("site", null);
        }
        // 设置分页，page里面包含了分页信息
        Page<Object> page = PageHelper.startPage(pager.getNowPage(), pager.getPageSize());
        List<Map<String,Object>> list = iIpAddressUserLogService.queryIpLogByType(parameters);
        return getReturnPage(pager, page, list);
    }

}
