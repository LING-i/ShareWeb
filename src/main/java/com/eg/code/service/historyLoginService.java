package com.eg.code.service;


import com.eg.code.entity.historyLogin;

import java.util.Date;
import java.util.List;

/**
 * 每日登录数
 */
public interface historyLoginService {

    //插入一条记录
    public void addLogin(historyLogin historyLogin);

    //查询一条记录
    public Integer selectLogin(String day);

    //更新用户登录数
    public void  update(String day);

    //七天内的登录数
    public List<Integer> selectLoginList(String day1, String day2);



}
