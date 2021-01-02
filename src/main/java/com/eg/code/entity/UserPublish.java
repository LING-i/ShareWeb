package com.eg.code.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="userPublish")
@JsonIgnoreProperties(value={"hibernateLazyInitializer","hander","fieldHandler"})
public class UserPublish implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userPublishId;          //用户发布的id

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publishDate;              //发布的时间

    @ManyToOne
    @JoinColumn(name="articleId")
    private Article article;                //发布的资源

    @ManyToOne
    @JoinColumn(name="userId")
    private User user;

    public Integer getUserPublishId() {
        return userPublishId;
    }

    public void setUserPublishId(Integer userPublishId) {
        this.userPublishId = userPublishId;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserPublish() {
    }
}
