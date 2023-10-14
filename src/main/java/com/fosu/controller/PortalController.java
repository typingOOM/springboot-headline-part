package com.fosu.controller;

import com.fosu.pojo.vo.PortalVo;
import com.fosu.service.HeadlineService;
import com.fosu.service.TypeService;
import com.fosu.utils.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@ResponseBody
@Controller
@RequestMapping("portal")
public class PortalController {

    @Autowired
    private TypeService typeService;

    @Autowired
    private HeadlineService headlineService;

    @GetMapping("findAllTypes")
    public Result findAllTypes(){
        return typeService.getAll();
    }

    @PostMapping("findNewsPage")
    public Result findNewsPage(@RequestBody PortalVo portalVo){
        System.out.println(portalVo);
        return headlineService.findNewsPage(portalVo);

    }

    @PostMapping("showHeadlineDetail")
    public Result showHeadlineDetail(@Param("hid") Integer hid){
        return headlineService.showHeadlineDetail(hid);
    }
}
