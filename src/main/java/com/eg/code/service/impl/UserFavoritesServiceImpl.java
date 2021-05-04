package com.eg.code.service.impl;

import com.eg.code.entity.Fkey;
import com.eg.code.entity.UserDownload;
import com.eg.code.entity.UserFavorites;
import com.eg.code.repository.UserFavoritesRespository;
import com.eg.code.service.UserFavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.Optional;

@Service("userFavoritesService")
@Transactional
public class UserFavoritesServiceImpl implements UserFavoritesService {

    @Autowired
    private UserFavoritesRespository userFavoritesRespository;


    @Override
    public void save(UserFavorites userFavorites) {
        userFavoritesRespository.save(userFavorites);
    }

    @Override
    public UserFavorites getById(Fkey fkey) {
        Optional<UserFavorites> one = userFavoritesRespository.findById(fkey);
        UserFavorites userFavorites = one.orElse(null);
        if(userFavorites == null){
            return  null;
        }else{
            return userFavorites;
        }
    }

    @Override
    public Page<UserFavorites> list(Integer userId, Integer page, Integer pageSize, Sort.Direction direction, String... properties) {
        return userFavoritesRespository.findAll(new Specification<UserFavorites>() {
            @Override
            public Predicate toPredicate(Root<UserFavorites> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.conjunction();
                if (userId != null) {
                    predicate.getExpressions().add(criteriaBuilder.equal(root.get("UserId").as(Integer.class), userId));
                }
                return predicate;
            }
        }, PageRequest.of(page - 1, pageSize, direction, properties));
    }
}
