package com.fosu.service;

import com.fosu.pojo.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fosu.utils.Result;

import java.util.List;

/**
* @author Hao
* @description 针对表【news_type】的数据库操作Service
* @createDate 2023-10-12 20:01:06
*/
public interface TypeService extends IService<Type> {

    Result getAll();
}
