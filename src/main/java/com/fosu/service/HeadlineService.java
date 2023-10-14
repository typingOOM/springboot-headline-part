package com.fosu.service;

import com.fosu.pojo.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fosu.pojo.vo.PortalVo;
import com.fosu.utils.Result;

/**
* @author Hao
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2023-10-12 20:01:06
*/
public interface HeadlineService extends IService<Headline> {

    Result findNewsPage(PortalVo portalVo);

    Result showHeadlineDetail(Integer hid);

    Result publish(Headline headline);

    Result findHeadlineByHid(int hid);

    Result updateHeadline(Headline headline);
}
