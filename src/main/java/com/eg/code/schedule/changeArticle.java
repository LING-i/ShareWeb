package com.eg.code.schedule;



import com.eg.code.entity.Article;
import com.eg.code.entity.User;
import com.eg.code.repository.ArticleRepository;
import com.eg.code.run.StartupRunner;
import com.eg.code.service.ArticleService;
import com.eg.code.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 定时任务
 */
@Component
@EnableScheduling
public class changeArticle {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

    @Autowired
    private StartupRunner startupRunner;

    @Autowired
    private ArticleRepository articleRepository;


    //定时任务 [秒] [分] [小时] [日] [月] [周] [年] - 凌晨 00 ：15分
    @Scheduled(cron="0 15 0 * * ?")
    public void changePoints(){
     //定时任务，给资源发布者加积分条件：1.若浏览量和分享量达标，那么就给发布者加30积分。
        //1、查资源的articleId、userid、share、click、flag
        //2、若flag = 0，且click > 150 或者 share > 40 就给user的points+30

        List<Article> articles = articleService.listflag();
        for (Article article : articles) {
            if(article.getClick() > 200 || article.getShare() > 50){
                //用户积分加30
                User user = article.getUser();
                user.setPoints(user.getPoints()+30);
                userService.save(user);

                //将article的isFalg = 1;
                articleService.changeFalg(article.getArticleId());

            }
        }
    }

    @Scheduled(cron="0 26 * * * ?")
    public void changeHot(){

        //设置热门资源
        List<Article> articles = articleService.listhot();
        for (Article article: articles) {
            Integer articleId = article.getArticleId();
            //热门资源的条件
            if(article.getClick() > 200){
               //通过articleId将article的hot更改。
                articleService.changeHot(articleId);
                //将redis中的资源缓存删除
                redisTemplate.opsForList().remove("allarticleId",0,articleId);
                int arcTypeId = articleRepository.getArcTypeIdByArticleId(articleId);
                redisTemplate.opsForList().remove("article_type_"+arcTypeId,0,articleId);
                redisTemplate.delete("article_"+articleId);

                startupRunner.loadData();//刷新缓存
            }

        }


    }















}
