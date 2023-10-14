package com.fosu.service;

import com.fosu.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fosu.utils.Result;

/**
* @author Hao
* @description 针对表【news_user】的数据库操作Service
* @createDate 2023-10-12 20:01:06
*/
public interface UserService extends IService<User> {

    Result<User> login(User user);

    Result getUserInfo(String token);

    Result checkUserName(String username);

    Result register(User user);
}
