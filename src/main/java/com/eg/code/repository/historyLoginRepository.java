package com.eg.code.repository;

import com.eg.code.entity.historyLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;


public interface historyLoginRepository extends JpaRepository<historyLogin,String>, JpaSpecificationExecutor<historyLogin> {

    //更新用户数 + 1
    @Modifying
    @Transactional
    @Query(value = "update  history_login  set num=num+1 where day = ?1",nativeQuery = true)
    public void updateLogin(String day);

    //查询用户数
    @Query(value = "select num from history_login  where day = ?1",nativeQuery = true)
    public Integer selectLogin(String day);

    //查询七天的用户数
    @Query(value = "select num from history_login  where day  >= ?1 and  day <= ?2",nativeQuery = true)
    public List<Integer> selectLoginList(String day1,String day2);
}
