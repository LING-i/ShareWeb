package com.eg.code.controller.admin;

import com.eg.code.entity.Data;
import com.eg.code.service.ArticleService;
import com.eg.code.service.UserService;
import com.eg.code.service.historyLoginService;
import com.eg.code.util.DateUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 管理员 - 首页或者跳转url控制器
 */
@Controller
public class IndexAdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private historyLoginService loginService;

    /**
     * 跳转到管理员主页面
     */
    @RequiresPermissions(value="进入管理员主页")
    @RequestMapping("/toAdminUserCenterPage")
    public String toAdminUserCenterPage(){
        return "admin/index";
    }

    /**
     * 跳转到管理员主页面
     */
    @RequiresPermissions(value="进入管理员主页")
    @RequestMapping("/defaultIndex")
    public ModelAndView defaultIndex(){
        ModelAndView mav = new ModelAndView();
        mav.addObject("userNum",userService.getCount(null,null,null));
        mav.addObject("todayRegister",userService.todayRegister());
        mav.addObject("todayLogin",userService.todayLogin());
        mav.addObject("todayPublish",articleService.todayPublish());
        mav.addObject("noAudit",articleService.noAudit());
        mav.setViewName("admin/default");
        return mav;
    }

    @RequiresPermissions(value="加载图表数据")
    @ResponseBody
    @GetMapping("/getUserLogin")
    public Data getUserLogin(){
      //请求达到需要返回Json格式的data
      // 1.包括日期categories和每个日期对应的用户登录数data
      Data data = new Data();
      //1.写一个日期工具类缓存过去七天（包括今天）的日期。只包括 月份-日期
        List<String> cateList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        for(int i = 0;i<7;i++){
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, - i);
            Date monday = c.getTime();
            String preMonday = sdf.format(monday);
            cateList.add(preMonday);
        }
        Collections.reverse(cateList);
        data.setCategories(cateList);
        // a.得建表history_login存储每天登录的用户数。
        // b.查询用户表将今日登录的用户数写进history_login
      //2.查询过去七天，每天的登录用户数，将其写入data.data中。
        //用户登录的时候就写history_login表。

        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, - 6);
        Date monday = c.getTime();
        String date1 = DateUtil.formatDate(monday,"yyyy-MM-dd");//6天前
        String date2 = DateUtil.formatDate(new Date(), "yyyy-MM-dd");//今天
        List<Integer> dataInt = loginService.selectLoginList(date1, date2);
        data.setData(dataInt);
        return data;
    }

    @RequiresPermissions(value="加载图表数据")
    @ResponseBody
    @GetMapping("/getUserNums")
    public Data getUserNums(){
        //请求达到需要返回Json格式的data
        // 1.包括日期categories和每个日期对应的用户登录数data
        Data data = new Data();
        //1.写一个日期工具类缓存过去七天（包括今天）的日期。只包括 月份-日期
        List<String> cateList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        for(int i = 0;i<7;i++){
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, - i);
            Date monday = c.getTime();
            String preMonday = sdf.format(monday);
            cateList.add(preMonday);
        }
        Collections.reverse(cateList);
        data.setCategories(cateList);
        // a.得建表history_login存储每天登录的用户数。
        // b.查询用户表将今日登录的用户数写进history_login
        //2.查询过去七天，每天的登录用户数，将其写入data.data中。
        //用户登录的时候就写history_login表。
//
//        Calendar c = Calendar.getInstance();
//        c.add(Calendar.DATE, - 6);
//        Date monday = c.getTime();
//        String date1 = DateUtil.formatDate(monday,"yyyy-MM-dd");//6天前
//        String date2 = DateUtil.formatDate(new Date(), "yyyy-MM-dd");//今天
//        List<Integer> dataInt = loginService.selectLoginList(date1, date2);

        List<Integer> dataInt = new ArrayList<>();
        dataInt.add(50);
        dataInt.add(51);
        dataInt.add(63);
        dataInt.add(65);
        dataInt.add(66);
        dataInt.add(70);
        dataInt.add(73);

        data.setData(dataInt);
        return data;
    }


}
