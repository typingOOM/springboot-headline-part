package com.fosu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fosu.pojo.Type;
import com.fosu.service.TypeService;
import com.fosu.mapper.TypeMapper;
import com.fosu.utils.Result;
import com.fosu.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Hao
* @description 针对表【news_type】的数据库操作Service实现
* @createDate 2023-10-12 20:01:06
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public Result getAll() {
        List<Type> types = typeMapper.selectList(null);

        return Result.build(types, ResultCodeEnum.SUCCESS);
    }
}




