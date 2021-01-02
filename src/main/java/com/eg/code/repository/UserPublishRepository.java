package com.eg.code.repository;

import com.eg.code.entity.UserDownload;
import com.eg.code.entity.UserPublish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * 用户发布的接口
 */
public interface UserPublishRepository extends JpaRepository<UserPublish,Integer>, JpaSpecificationExecutor<UserPublish> {


    @Query(value ="delete from userPublish where articleId = ?",nativeQuery = true)
    void deleteByArticleId(int articleId);

}
