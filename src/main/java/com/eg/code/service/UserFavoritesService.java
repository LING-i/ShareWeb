package com.eg.code.service;

import com.eg.code.entity.Fkey;
import com.eg.code.entity.UserFavorites;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

public interface UserFavoritesService {
/**
 * 用户收藏接口
  */



    public void save(UserFavorites userFavorites);

    public UserFavorites getById(Fkey fkey);

    /**
     * 分页查询某个用户收藏的所有资源
     */
    public Page<UserFavorites> list(Integer userId, Integer page, Integer pageSize, Sort.Direction direction, String... properties);



}
