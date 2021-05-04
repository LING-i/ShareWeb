package com.eg.code.controller;

import com.eg.code.entity.Article;
import com.eg.code.entity.Fkey;
import com.eg.code.entity.User;
import com.eg.code.entity.UserFavorites;
import com.eg.code.lucene.ArticleIndex;
import com.eg.code.service.ArcTypeService;
import com.eg.code.service.ArticleService;
import com.eg.code.service.UserFavoritesService;
import com.eg.code.service.UserService;
import com.eg.code.util.Consts;
import com.eg.code.util.HTMLUtil;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资源控制器
 */
@RestController
@RequestMapping(value = "/article")
public class ArticleController {

    @Autowired
    private ArcTypeService arcTypeService;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private ArticleIndex articleIndex;

    @Autowired
    private UserService userService;

    @Autowired
    private UserFavoritesService userFavoritesService;

    /**
     * 按资源类型分页查询资源列表
     * @param type
     * @param currentPage
     * @return
     */
    @RequestMapping("/{type}/{currentPage}")
    public ModelAndView type(@PathVariable(value = "type",required = false) String type, @PathVariable(value = "currentPage",required = false)Integer currentPage){
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        //类型的html代码
        List arcTypleList = arcTypeService.listAll(Sort.Direction.ASC,"sort");
        mav.addObject("arcTypeList",arcTypleList);
        mav.addObject("arcTypeStr", HTMLUtil.getArcTypeStr(type,arcTypleList));
        //资源列表
        Map<String,Object> map = articleService.list(type,currentPage, Consts.PAGE_SIZE);
        mav.addObject("articleList",map.get("data"));
        //分页html代码
        mav.addObject("pageStr",HTMLUtil.getPagation("/article/"+type,Integer.parseInt(String.valueOf(map.get("count"))),currentPage,"该分类还没有数据..."));
        return mav;
    }

    /**
     * 关键字分词搜索
     */
    @RequestMapping("/search")
    public ModelAndView search(String keywords,@RequestParam(value = "page",required = false) Integer page) throws ParseException, InvalidTokenOffsetsException, org.apache.lucene.queryparser.classic.ParseException, IOException {
        if(page==null){
            page = 1;
        }
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        //类型的html代码
        List arcTypleList = arcTypeService.listAll(Sort.Direction.ASC,"sort");
        mav.addObject("arcTypeList",arcTypleList);

        //根据关键词查找资源
        List<Article> articleList = articleIndex.search(keywords);
        Integer toIndex = articleList.size()>=page*Consts.PAGE_SIZE?page*Consts.PAGE_SIZE:articleList.size();

        mav.addObject("articleList",articleList.subList((page-1)*Consts.PAGE_SIZE,toIndex));
        mav.addObject("keywords",keywords);

        //分页html代码
        int totalPage = articleList.size()%Consts.PAGE_SIZE==0?articleList.size()/Consts.PAGE_SIZE:articleList.size()/Consts.PAGE_SIZE+1;
        String targetUrl = "/article/search?keywords="+keywords;
        String msg = "没有关键字是 \"<font style=\"border: 0px;color:red;font-weight:bold;padding-left: 3px; padding-right: 3px;\" >" +keywords + "</font>\" 的相关资源！";
        mav.addObject("pageStr",HTMLUtil.getPagation2(targetUrl,totalPage,page,msg));
        return mav;
    }

    /**
     * 资源详情
     */
    @RequestMapping("/detail/{articleId}")
    public ModelAndView detail(@PathVariable(value = "articleId",required = false) String articleId) throws IOException, org.apache.lucene.queryparser.classic.ParseException {
        ModelAndView mav = new ModelAndView();
        String replace = articleId.replace(".html","");
        articleService.updateClick(Integer.parseInt(replace));
        Article article = articleService.getById(Integer.parseInt(replace));
        if(article.getState()!=2){
            return null;
        }
        mav.addObject("article",article);
        //类型的html代码
        List arcTypleList = arcTypeService.listAll(Sort.Direction.ASC,"sort");
        mav.addObject("arcTypeList",arcTypleList);

        //通过lucene分词查找相似资源
        List<Article> articleList = articleIndex.searchNoHighLighter(article.getName().replace("视频","").replace("教程","")
                            .replace("下载","").replace("PDF",""));
        if(articleList!=null&&articleList.size()>0){
            mav.addObject("similarityArticleList",articleList);
        }
        mav.setViewName("detail");
        return mav;
    }

    /**
     * 分享数 + 1
     */
    @RequestMapping("/share")
    public void share(Integer articleId){
        //分享数 + 1
        articleService.updateShare(articleId);

    }


    @RequestMapping("/favorites")
    public Map<String, Object> setFavorites(Integer articleId,Boolean item, HttpSession session){
        HashMap<String,Object> map = new HashMap<>();
        User user = (User) session.getAttribute(Consts.CURRENT_USER);
        UserFavorites userFavorites = new UserFavorites();
        userFavorites.setArticleId(articleId);
        userFavorites.setUserId(user.getUserId());
        userFavorites.setFavorites(item);
        userFavorites.setFavoritesDate(new Date());
        userFavoritesService.save(userFavorites);
        map.put("success",true);
        return  map;
    }


    @RequestMapping("/isFavorites")
    public Map<String, Object> isFavorites(Integer articleId, HttpSession session){
        HashMap<String,Object> map = new HashMap<>();
        User user = (User) session.getAttribute(Consts.CURRENT_USER);
        Fkey fkey = new Fkey();
        fkey.setArticleId(articleId);
        fkey.setUserId(user.getUserId());
        UserFavorites favorites = userFavoritesService.getById(fkey);
        if(favorites == null){
            map.put("flag",false);
        }else{
            map.put("flag",favorites.isFavorites());
        }
        return  map;
    }

    /**
     * 判断资源是否免费
     */
    @ResponseBody
    @RequestMapping("/isFree")
    public boolean isFree(Integer articleId){
        Article article = articleService.getById(articleId);
        return article.isFree();
    }





}
