package com.eg.code.entity;

import javax.persistence.Id;
import java.io.Serializable;

public class Fkey implements Serializable {


    private Integer articleId;


    private Integer UserId;


    public Fkey() {
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public Integer getUserId() {
        return UserId;
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }
}
