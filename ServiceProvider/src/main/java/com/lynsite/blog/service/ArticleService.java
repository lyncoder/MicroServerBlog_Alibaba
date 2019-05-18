package com.lynsite.blog.service;

import com.lynsite.blog.entity.Article;

import java.util.List;

/**
 * @Description: TODO
 * @Author: 刘亚楠
 * @Date: 2019/3/6 13:52
 * @Version: 1.0
 */
public interface ArticleService {

    Article queryArticleById(Long id);

    List<Article> queryArticleByIds(List<Long> ids);

    List<Article> selectAll();

    int insert(Article record);

    int insertSelective(Article record);

}
