package com.fosu.controller;

import com.fosu.pojo.Headline;
import com.fosu.service.HeadlineService;
import com.fosu.utils.JwtHelper;
import com.fosu.utils.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@ResponseBody
@Controller
@RequestMapping("headline")
@CrossOrigin
public class HeadlineController {

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private HeadlineService headlineService;

    @PostMapping("publish")
    public Result publish(@RequestBody Headline headline, @RequestHeader String token){
//        if (!token.isEmpty() && !jwtHelper.isExpiration(token)){
//            Long userId = jwtHelper.getUserId(token);
//            headline.setPublisher(userId.intValue());
//
//            return headlineService.publish(headline);
//        }
//
//        return Result.build(null, ResultCodeEnum.NOTLOGIN);
        Long userId = jwtHelper.getUserId(token);
        headline.setPublisher(userId.intValue());
        return headlineService.publish(headline);
    }

    @PostMapping("findHeadlineByHid")
    public Result findHeadlineByHid(@Param(value = "hid") int hid){

        return headlineService.findHeadlineByHid(hid);
    }

    @PostMapping("update")
    public Result update(@RequestBody Headline headline){

        return headlineService.updateHeadline(headline);
    }

    @PostMapping("removeByHid")
    public Result removeByHid(@Param(value = "hid") int hid){
        System.out.println("hid = " + hid);
        boolean b = headlineService.removeById(hid);
        System.out.println(b);
        return Result.ok(null);
    }


}
