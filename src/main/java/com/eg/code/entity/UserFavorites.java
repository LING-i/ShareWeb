package com.eg.code.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Proxy;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "user_favorites")
@Proxy(lazy = false)
@IdClass(Fkey.class)
@JsonIgnoreProperties(value={"hibernateLazyInitializer","hander","fieldHandler"})
public class UserFavorites implements Serializable {

    @Id
    private Integer articleId;

    @Id
    private Integer UserId;

    private boolean isFavorites = false; //是否收藏

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date favoritesDate;              //下载时间

    public UserFavorites() {
    }

    public UserFavorites(Integer articleId, Integer userId) {
        this.articleId = articleId;
        UserId = userId;
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

    public boolean isFavorites() {
        return isFavorites;
    }

    public void setFavorites(boolean favorites) {
        isFavorites = favorites;
    }

    public Date getFavoritesDate() {
        return favoritesDate;
    }

    public void setFavoritesDate(Date favoritesDate) {
        this.favoritesDate = favoritesDate;
    }

    @Override
    public String toString() {
        return "UserFavorites{" +
                "articleId=" + articleId +
                ", UserId=" + UserId +
                ", isFavorites=" + isFavorites +
                ", favoritesDate=" + favoritesDate +
                '}';
    }
}
