package com.eg.code.repository;

import com.eg.code.entity.Fkey;
import com.eg.code.entity.UserFavorites;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserFavoritesRespository extends JpaRepository<UserFavorites,Fkey>, JpaSpecificationExecutor<UserFavorites> {


}
