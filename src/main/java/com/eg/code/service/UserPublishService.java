package com.eg.code.service;

import com.eg.code.entity.Article;
import com.eg.code.entity.UserDownload;
import com.eg.code.entity.UserPublish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface UserPublishService {

    /**
     * 分页查询某个用户发布的所有资源
     */
    public Page<UserPublish> list(Integer userId, Integer page, Integer pageSize, Sort.Direction direction, String... properties);

    /**
     * 资源审核通过的时候，在关联表插入数据
     */
    public  void addPublishArtcile(Article article);

    /**
     * 删除userPublish中对应数据
     * @param articleId
     */
    public void deleteByArticleId (int articleId);


}
