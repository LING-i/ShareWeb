package com.eg.code.repository;

import com.eg.code.entity.UserDownload;
import com.eg.code.entity.UserPublish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 用户发布的接口
 */
public interface UserPublishRepository extends JpaRepository<UserPublish,Integer>, JpaSpecificationExecutor<UserPublish> {

    @Modifying
    @Query(value ="delete from user_publish where article_id = ?1",nativeQuery = true)
    void deleteByArticleId(int articleId);

}
