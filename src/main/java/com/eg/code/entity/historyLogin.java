package com.eg.code.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="history_login")
@Proxy(lazy = false)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","hander","fieldHandler"})
public class historyLogin implements Serializable {

    @Id
    private String day;      //登录时间


    private Integer num = 0;

    public historyLogin() {
    }

    public String getLoginDate() {
        return day;
    }


    public historyLogin(String loginDate, Integer num) {
        this.day = loginDate;
        this.num = num;
    }

    public void setRegistrationDate(String loginDate) {
        this.day = loginDate;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }
}
