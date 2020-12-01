package com.atlp.eduteacher.controller;

import com.atlp.commonutils.R;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin // 标识跨域访问注解
public class EduLoginController {

    @PostMapping("login")
    public R login() {
        return R.ok().data("token", "admin");
    }

    @GetMapping("info")
    public R info() {
        return R.ok().data("name", "[admin]").data("avatar", "http://icon.nipic.com/BannerPic/20200303/original/20200303163916_1.jpg");
    }
}
