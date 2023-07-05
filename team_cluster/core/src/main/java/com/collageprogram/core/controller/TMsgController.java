package com.collageprogram.core.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.collageprogram.common.entity.R;
import com.collageprogram.core.entity.TUser;
import com.collageprogram.core.mapper.TMsgMapper;
import com.collageprogram.core.mapper.TTeamMapper;
import com.collageprogram.core.mapper.TUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author binggoCoder
 * @since 2023-06-30
 */
@RestController
@RequestMapping("/t-msg")
public class TMsgController {
    @Autowired
    TMsgMapper tMsgMapper;
    @Autowired
    TUserMapper tUserMapper;

    @GetMapping("/list")
    public R list() {
        System.out.println("msg list");
        TUser user = tUserMapper.selectOne(new QueryWrapper<TUser>().eq("uid", String.valueOf(StpUtil.getLoginId())));

        return R.ok(tMsgMapper.selectList
                (new QueryWrapper<com.collageprogram.core.entity.TMsg>().eq("username", user.getUserName())));
    }

}
