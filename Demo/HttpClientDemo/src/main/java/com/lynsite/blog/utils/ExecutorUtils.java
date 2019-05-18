package com.lynsite.blog.utils;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 模拟并发访问线程工具类
 * @Author: 刘亚楠
 * @Date: 2019/5/15 15:11
 * @Version: 1.0
 */
public class ExecutorUtils {

    private static final Random random = new Random();
    private static final Integer[] ips = {8090, 8091};
//    private static final Integer[] ips = {13001, 13002};

    private static final String[] urls = {"/hello/asd","/hello1/asd"};

    public static void main(String[] args) {

        ExecutorService service = Executors.newFixedThreadPool(100);
        for(int i=0; i<30; i++){
            service.execute( () -> {
                for (int j=0; j<200; j++){
                    System.out.println(ips[random.nextInt(2)]);
                    String url = "http://localhost:" + ips[random.nextInt(2)] + urls[random.nextInt(2)];
                    HttpClientUtils.doGet(url);
                    try {
                        Thread.sleep(random.nextInt(1500));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            service.shutdown();
            service.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
