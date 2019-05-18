package com.lynsite.blog.controller;

import com.lynsite.blog.feign.ArticleClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description: TODO
 * @Author: 刘亚楠
 * @Date: 2019/4/13 22:44
 * @Version: 1.0
 */

@RestController
@RequestMapping("article")
public class ArticleController {

    @Autowired
    private ArticleClientService articleClientService;

    @GetMapping("/hello/{name}")
    public String hello(@PathVariable("name") String name){
        return articleClientService.hello(name);
    }

    @GetMapping("/hello1/{name}")
    public String hello1(@PathVariable("name") String name){
        return articleClientService.hello1(name);
    }

    @GetMapping("/get/{id}")
    public Object queryArticleById(@PathVariable("id") Long id){
        return articleClientService.queryArticleById(id);
    }

    @GetMapping("/gets/{ids}")
    public Object queryArticleByIds(@PathVariable("ids") List<Long> ids){
        return articleClientService.queryArticleByIds(ids);
    }

    @GetMapping("/list")
    public Object queryAllArticles(){
        return articleClientService.queryAllArticles();
    }

}
