package com.collageprogram.core.controller;


import cn.dev33.satoken.annotation.SaIgnore;
import com.collageprogram.common.entity.R;
import com.collageprogram.core.entity.TUser;
import com.collageprogram.core.service.ITUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author binggoCoder
 * @since 2023-06-29
 */
@RestController
@RequestMapping("/t-user")
@SaIgnore
public class TUserController {
    @Autowired
    ITUserService itUserService;

    @RequestMapping("/test")
    public R test() {
        TUser tUser = new TUser();
        tUser.setUserName("test");
        itUserService.save(tUser);
        return R.ok();
    }

    @GetMapping("/list")
    public R list() {
        System.out.println("user list");
        List<String> collect = itUserService.list().stream().map(TUser::getUserName).collect(Collectors.toList());
        return R.ok(collect);
    }
}
