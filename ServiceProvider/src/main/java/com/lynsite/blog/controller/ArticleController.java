package com.lynsite.blog.controller;

import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.lynsite.blog.constant.Constants;
import com.lynsite.blog.exception.ResultException;
import com.lynsite.blog.entity.Article;
import com.lynsite.blog.sentinel.handler.SentinelExceptionHandler;
import com.lynsite.blog.service.ArticleService;
import com.lynsite.blog.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @Author: 刘亚楠
 * @Date: 2019/3/8 11:06
 * @Version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("article")
public class ArticleController {

    Logger LOG = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    @GetMapping("/hello/{name}")
    @SentinelResource(value = "hello", entryType = EntryType.OUT,
            blockHandlerClass = SentinelExceptionHandler.class, blockHandler = "blockExceptionHandle")
    public String hello(@PathVariable("name") String name) {
        System.out.println(name);
        return "hello " + name;
    }

    @GetMapping("/hello1/{name}")
    @SentinelResource(value = "hello1", entryType = EntryType.OUT,
            blockHandlerClass = SentinelExceptionHandler.class, blockHandler = "blockExceptionHandle1")
    public String hello1(@PathVariable("name") String name) {
        System.out.println(name);
        return "hello1 " + name;
    }

    @GetMapping("/get/{id}")
    public ResultVO queryArticleById(@PathVariable("id") Long id){
        ResultVO vo = new ResultVO(false, Constants.FAILED_MSG, 0L, null);
        try {
            Article article = articleService.queryArticleById(id);
            List<Article> list = new ArrayList<>();
            list.add(article);
            vo.setSuccess(true);
            vo.setMsg(Constants.SUCCESS_MSG);
            vo.setTotal((long)list.size());
            vo.setData(list);
        }catch (ResultException e){
            LOG.error("queryArticleById: {}", e);
            vo.setMsg(e.getMessage());
        }
        return vo;
    }

    @GetMapping("/gets/{ids}")
    public ResultVO queryArticleByIds(@PathVariable("ids") List<Long> ids){
        ResultVO vo = new ResultVO(false, Constants.FAILED_MSG, 0L, null);
        try {
            List<Article> list = articleService.queryArticleByIds(ids);
            vo.setSuccess(true);
            vo.setMsg(Constants.SUCCESS_MSG);
            vo.setTotal((long)list.size());
            vo.setData(list);
        }catch (ResultException e){
            LOG.error("queryArticleByIds: {}", e);
            vo.setMsg(e.getMessage());
        }
        return vo;
    }

    @GetMapping("/list")
    public ResultVO queryAllArticles(){
        ResultVO vo = new ResultVO(false, Constants.FAILED_MSG, 0L, null);
        try {
            List<Article> list = articleService.selectAll();
            vo.setSuccess(true);
            vo.setMsg(Constants.SUCCESS_MSG);
            vo.setTotal((long)list.size());
            vo.setData(list);
        }catch (ResultException e){
            LOG.error("queryAllArticles: {}", e);
            vo.setMsg(e.getMessage());
        }
        return vo;
    }

}
