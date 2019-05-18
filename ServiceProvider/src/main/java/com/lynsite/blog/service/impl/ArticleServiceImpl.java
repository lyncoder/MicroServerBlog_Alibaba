package com.lynsite.blog.service.impl;

import com.lynsite.blog.dao.ArticleMapper;
import com.lynsite.blog.entity.Article;
import com.lynsite.blog.enums.ResultEnums;
import com.lynsite.blog.exception.ResultException;
import com.lynsite.blog.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @Author: 刘亚楠
 * @Date: 2019/3/16 15:48
 * @Version: 1.0
 */
@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    Logger LOG = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Article queryArticleById(Long id) {
        try{
            Article article = articleMapper.selectByPrimaryKey(id);
            if(article == null){
                throw new ResultException(ResultEnums.RESULT_NOT_FOUND);
            }
            return article;
        }catch (Exception e){
            LOG.error("queryArticleById: {}", e);
            throw new ResultException(ResultEnums.RESULT_ERROR);
        }
    }

    @Override
    public List<Article> queryArticleByIds(List<Long> ids) {
        try{
            List<Article> articles = new ArrayList<>();
            ids.forEach(id -> {
                articles.add(articleMapper.selectByPrimaryKey(id));
            });
            if(articles.size() == 0){
                throw new ResultException(ResultEnums.RESULT_NOT_FOUND);
            }
            return articles;
        }catch (Exception e){
            LOG.error("queryArticleById: {}", e);
            throw new ResultException(ResultEnums.RESULT_ERROR);
        }
    }

    @Override
    public List<Article> selectAll() {
        try{
            List<Article> articles = articleMapper.selectAll();
            if(articles.size() == 0){
                throw new ResultException(ResultEnums.RESULT_NOT_FOUND);
            }
            return articles;
        }catch (Exception e){
            LOG.error("queryArticleById: {}", e);
            throw new ResultException(ResultEnums.RESULT_ERROR);
        }

    }

    @Override
    public int insert(Article record) {
        return 0;
    }

    @Override
    public int insertSelective(Article record) {
        return 0;
    }
}
