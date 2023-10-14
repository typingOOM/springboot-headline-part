package com.fosu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fosu.pojo.Headline;
import com.fosu.pojo.vo.PortalVo;
import com.fosu.service.HeadlineService;
import com.fosu.mapper.HeadlineMapper;
import com.fosu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author Hao
* @description 针对表【news_headline】的数据库操作Service实现
* @createDate 2023-10-12 20:01:06
*/
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
    implements HeadlineService{

    @Autowired
    private HeadlineMapper headlineMapper;

    @Override
    public Result findNewsPage(PortalVo portalVo) {
        //1.查询需要自定义语句 自定义mapper方法 携带分页
        //2.返回的结果是List<Map> 因为没有对应的实体类去接值

        IPage<Map> page = new Page(portalVo.getPageNum(), portalVo.getPageSize());
        headlineMapper.selectMyPage(page, portalVo);

        List<Map> records = page.getRecords();

        Map data = new HashMap();
        data.put("pageData", records);
        data.put("pageNum", page.getCurrent());
        data.put("pagSize", page.getSize());
        data.put("totalPage", page.getPages());
        data.put("totalSize", page.getTotal());

        Map pageInfo = new HashMap();
        pageInfo.put("pageInfo",data);

        return Result.ok(pageInfo);

    }

    @Override
    public Result showHeadlineDetail(Integer hid) {

        Map map = headlineMapper.showDetailById(hid);

        Headline headline = new Headline();
        headline.setHid(hid);
        headline.setPageViews((Integer) map.get("pageViews") + 1);
        headline.setVersion((Integer) map.get("version"));
        headlineMapper.updateById(headline);


        Map pageInfo = new HashMap();
        pageInfo.put("headline",map);

        return Result.ok(pageInfo);
    }

    @Override
    public Result publish(Headline headline) {

        headline.setPageViews(0);
        headline.setCreateTime(new Date());
        headline.setUpdateTime(new Date());

        headlineMapper.insert(headline);
        return Result.ok(null);
    }

    @Override
    public Result findHeadlineByHid(int hid) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.select("hid", "title", "article", "type");
        queryWrapper.eq("hid", hid);

        Map map = new HashMap();
        map.put("headline", headlineMapper.selectOne(queryWrapper));

        return Result.ok(map);
    }

    @Override
    public Result updateHeadline(Headline headline) {
        //进行修改操作时需要使用乐观锁
        Integer version = headlineMapper.selectById(headline.getHid()).getVersion();
        headline.setVersion(version);
        headline.setUpdateTime(new Date());
        headlineMapper.updateById(headline);

        return Result.ok(null);
    }
}




