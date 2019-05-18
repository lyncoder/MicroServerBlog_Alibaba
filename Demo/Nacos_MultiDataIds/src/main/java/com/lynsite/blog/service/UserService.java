package com.lynsite.blog.service;

import com.lynsite.blog.model.User;
import org.springframework.stereotype.Service;

/**
 * @Description: TODO
 * @Author: 刘亚楠
 * @Date: 2019/4/2 10:49
 * @Version: 1.0
 */
@Service
public interface UserService {

    User findById(Long id);

}
