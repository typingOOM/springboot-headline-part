package com.fosu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fosu.pojo.User;
import com.fosu.service.UserService;
import com.fosu.mapper.UserMapper;
import com.fosu.utils.JwtHelper;
import com.fosu.utils.MD5Util;
import com.fosu.utils.Result;
import com.fosu.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
* @author Hao
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2023-10-12 20:01:06
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtHelper jwtHelper;



    @Override
    public Result login(User user) {

        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getUsername, user.getUsername());
        User loginUser = userMapper.selectOne(userLambdaQueryWrapper);

        if (loginUser != null){

            String password = MD5Util.encrypt(user.getUserPwd());

            if (loginUser.getUserPwd().equals(password)) {
                String token = jwtHelper.createToken((long) loginUser.getUid());
                Map map = new HashMap();
                map.put("token",token);
                return Result.build(map, ResultCodeEnum.SUCCESS);
            }
            return Result.build(null, ResultCodeEnum.PASSWORD_ERROR);

            }
        return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
    }

    @Override
    public Result getUserInfo(String token) {

        if (!jwtHelper.isExpiration(token)) {

            Long userId = jwtHelper.getUserId(token);
            LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper();

            lambdaQueryWrapper.eq(User::getUid, userId);
            lambdaQueryWrapper.select(User::getUid, User::getUsername, User::getNickName);
            User returnUser = userMapper.selectOne(lambdaQueryWrapper);

            if ( returnUser != null){
                Map map = new HashMap();
                map.put("loginUser", returnUser );
                return Result.build(map, ResultCodeEnum.SUCCESS);
            }
        }
        return Result.build(null, ResultCodeEnum.NOTLOGIN);
    }

    @Override
    public Result checkUserName(String username) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(User::getUsername);
        queryWrapper.eq(User::getUsername, username);
        if (userMapper.selectCount(queryWrapper) > 0){
            return Result.build(null, ResultCodeEnum.USERNAME_USED);
        }

        return Result.ok(null);
    }

    @Override
    public Result register(User user) {

        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.select(User::getUsername);
        lambdaQueryWrapper.eq(User::getUsername, user.getUsername());
        //lambdaQueryWrapper.eq(User::getUserPwd, user.getUserPwd());

        Long count = userMapper.selectCount(lambdaQueryWrapper);
        if (count > 0)
            return Result.build(null, ResultCodeEnum.USERNAME_USED);
        String encrypt = MD5Util.encrypt(user.getUserPwd());
        user.setUserPwd(encrypt);
        userMapper.insert(user);

        return Result.ok(null);
    }

}





