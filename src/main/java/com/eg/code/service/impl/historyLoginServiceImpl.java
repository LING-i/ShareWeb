package com.eg.code.service.impl;

import com.eg.code.entity.historyLogin;
import com.eg.code.repository.historyLoginRepository;
import com.eg.code.service.historyLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Transactional
@Service("historyLoginService")
public class historyLoginServiceImpl implements historyLoginService {

    @Autowired
    private historyLoginRepository loginRepository;

    @Override
    public void addLogin(historyLogin historyLogin) {
        loginRepository.save(historyLogin);
    }

    @Override
    public Integer selectLogin(String day) {
        return loginRepository.selectLogin(day);
    }

    @Override
    public void update(String day) {
        loginRepository.updateLogin(day);
    }

    @Override
    public List<Integer> selectLoginList(String day1, String day2) {
        return loginRepository.selectLoginList(day1,day2);
    }
}
