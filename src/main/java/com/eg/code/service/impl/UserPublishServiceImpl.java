package com.eg.code.service.impl;

import com.eg.code.entity.Article;
import com.eg.code.entity.UserPublish;
import com.eg.code.repository.UserPublishRepository;
import com.eg.code.service.UserPublishService;
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

@Service
@Transactional
public class UserPublishServiceImpl  implements UserPublishService {

    @Autowired
    private UserPublishRepository userPublishRepository;


    public Page<UserPublish> list(Integer userId, Integer page, Integer pageSize, Sort.Direction direction, String... properties) {
        return userPublishRepository.findAll(new Specification<UserPublish>() {
            @Override
            public Predicate toPredicate(Root<UserPublish> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate predicate = criteriaBuilder.conjunction();
                if(userId!=null){
                    predicate.getExpressions().add(criteriaBuilder.equal(root.get("user").get("userId"),userId));
                }
                return predicate;
            }
        }, PageRequest.of(page-1,pageSize,direction,properties));
    }

    @Override
    public void addPublishArtcile(Article article) {

            UserPublish userPublish = new UserPublish();
            userPublish.setUser(article.getUser());
            userPublish.setArticle(article);
            userPublish.setPublishDate(article.getPublishDate());
            userPublishRepository.save(userPublish);

    }

    @Override
    public void deleteByArticleId(int articleId) {
        userPublishRepository.deleteByArticleId(articleId);
    }
}
