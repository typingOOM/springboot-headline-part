package com.fosu.controller;

import com.fosu.pojo.User;
import com.fosu.service.UserService;
import com.fosu.utils.JwtHelper;
import com.fosu.utils.Result;
import com.fosu.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Controller
@ResponseBody
@RequestMapping("user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtHelper jwtHelper;


    @PostMapping("login")
    public Result login(@RequestBody User user){
        return userService.login(user);
    }

    @GetMapping("getUserInfo")
    public Result getUserInfo(@RequestHeader String token){
        return userService.getUserInfo(token);
    }

    @PostMapping("checkUserName")
    public Result checkUserName(String username){

        return userService.checkUserName(username);
    }

    @PostMapping("register")
    public Result register(@Validated @RequestBody User user){

        return userService.register(user);
    }

    @GetMapping("checkLogin")
    public Result checkLogin(@RequestHeader String token){
        if (!jwtHelper.isExpiration(token)) {
            return Result.ok(null);
        }

        return Result.build(null, ResultCodeEnum.LOGIN_EXPIRED);
    }



}
