package com.eg.code.repository;

import com.eg.code.util.DateUtil;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class historyLoginRepositoryTest {

    @Autowired
    private historyLoginRepository loginRepository;

    @Test
    void updateLogin() {
        String date = DateUtil.formatDate(new Date(), "yyyy-MM-dd");
        loginRepository.updateLogin(date);
    }


    @Test
    void selectLogin() throws ParseException {
        String date = DateUtil.formatDate(new Date(), "yyyy-MM-dd");
        System.out.println(loginRepository.selectLogin(date));
    }

    @Test
    void selectLoginList() throws ParseException {
        String date1 = "2021-04-22";
        String date2 = DateUtil.formatDate(new Date(), "yyyy-MM-dd");
        System.out.println(loginRepository.selectLoginList(date1, date2).toString());
    }

}