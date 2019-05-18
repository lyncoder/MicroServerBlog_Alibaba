package com.lynsite.blog.feign;

import com.lynsite.blog.vo.ResultVO;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @Description: feign负载均衡,客户端可依旧采用面向接口编程方式，controller中调用service
 * @Author: 刘亚楠
 * @Date: 2019/3/20 16:24
 * @Version: 1.0
 */
@FeignClient(value = "provider-article", fallbackFactory = ArticleFallbackFactory.class)
public interface ArticleClientService {

    /*
        TODO 该接口对应客户端中的controller，可直接通过注入bean的方式访问service
        TODO 映射路径为 provider 中的 URL
        TODO 该接口下的方法的熔断回滚函数都定义在  xxxFallbackFactory 中
    */

    @GetMapping("/article/hello/{name}")
    String hello(@PathVariable("name") String name);

    @GetMapping("/article/hello1/{name}")
    String hello1(@PathVariable("name") String name);

    @GetMapping("/article/get/{id}")
    Object queryArticleById(@PathVariable("id") Long id);

    @GetMapping("/article/gets/{ids}")
    Object queryArticleByIds(@PathVariable("ids") List<Long> ids);

    @GetMapping("/article/list")
    Object queryAllArticles();

}

@Component
@Slf4j
class ArticleFallbackFactory implements FallbackFactory<ArticleClientService> {

    Logger LOG = LoggerFactory.getLogger(ArticleFallbackFactory.class);

    @Override
    public ArticleClientService create(Throwable throwable) {
        return new ArticleClientService() {

            @Override
            public String hello(String name) {
                LOG.error("hello: {}, msg: {}", throwable.getMessage());
                return "hello failed";
            }

            @Override
            public String hello1(String name) {
                LOG.error("hello1: {}, msg: {}", throwable.getMessage());
                return "hello1 failed";
            }

            @Override
            public Object queryArticleById(Long id) {
                LOG.error("queryArticleById: {}, msg: {}", id, throwable.getMessage());
                return getVO("获取文章失败");
            }

            @Override
            public Object queryArticleByIds(List<Long> ids) {
                LOG.error("queryArticleByIds: {}, msg: {}", ids.toString(), throwable.getMessage());
                return getVO("查找文章列表失败");
            }

            @Override
            public Object queryAllArticles() {
                LOG.error("queryAllArticles: {}, msg: {}" + throwable.getMessage());
                return getVO("获取所有文章失败");
            }
        };
    }

    private ResultVO getVO(String msg){
        return new ResultVO<>(false, msg,0L, null);
    }
}
