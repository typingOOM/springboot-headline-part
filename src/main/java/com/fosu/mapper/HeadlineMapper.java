package com.fosu.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fosu.pojo.Headline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fosu.pojo.vo.PortalVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;

import java.util.Map;

/**
* @author Hao
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2023-10-12 20:01:06
* @Entity com.fosu.pojo.Headline
*/
public interface HeadlineMapper extends BaseMapper<Headline> {

    IPage<Map> selectMyPage(IPage page, @Param("portalVo") PortalVo portalVo);

    Map showDetailById(Integer hid);
}




