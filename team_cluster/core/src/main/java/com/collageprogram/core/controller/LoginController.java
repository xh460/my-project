package com.collageprogram.core.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.collageprogram.common.entity.LoginBody;
import com.collageprogram.common.entity.R;
import com.collageprogram.core.GlobalEntity.LoginUser;
import com.collageprogram.core.entity.TUser;
import com.collageprogram.core.enums.DeviceType;
import com.collageprogram.core.service.ITUserService;
import com.collageprogram.core.util.AjaxJson;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;

/**
 * @Auther: binggoCoder
 * @Date: 2023/6/29
 */
@RestController
@RequestMapping("/auth")
@SaIgnore
public class LoginController {
    @Autowired
    ITUserService itUserService;

    @RequestMapping("login")
    public AjaxJson login(@RequestParam(defaultValue = "10001") String id) {
        System.out.println(itUserService.list() + "list");
        System.out.println("======================= 进入方法，测试登录接口 ========================= ");
        System.out.println("当前会话的token：" + StpUtil.getTokenValue());
        System.out.println("当前是否登录：" + StpUtil.isLogin());
        System.out.println("当前登录账号：" + StpUtil.getLoginIdDefaultNull());

        StpUtil.login(id);            // 在当前会话登录此账号
        System.out.println("登录成功");
        System.out.println("当前是否登录：" + StpUtil.isLogin());
        System.out.println("当前登录账号：" + StpUtil.getLoginId());
//		System.out.println("当前登录账号并转为int：" + StpUtil.getLoginIdAsInt());
        System.out.println("当前登录设备：" + StpUtil.getLoginDevice());
//		System.out.println("当前token信息：" + StpUtil.getTokenInfo());

        return AjaxJson.getSuccess();
    }

    /**
     * 注册
     * @param user
     * @return
     */
    @PostMapping("/register")
public  R register(@RequestBody TUser user){
    System.out.println("进入到注册方法");
    System.out.println(user.toString());
    if (user.getUserName()==null || user.getPassWord()==null){
        return R.fail("账号或密码不能为空");
    }else {
        //存入数据库
        itUserService.save(user);
        return R.ok("注册成功");
    }
}
    @PostMapping("/loginUser")
    public R loginUser(@RequestBody LoginBody loginBody) {
        System.out.println("进入到登录方法");
        System.out.println(loginBody.toString());
        HashMap<String, String> ajax = new HashMap<String, String>();
        TUser user = itUserService.getOne(new QueryWrapper<TUser>().eq(("user_name"), loginBody.getUsername()));
        if (user.getUserName()==null || !user.getPassWord().equals(loginBody.getPassword())) {
            return R.fail("账号或密码错误");
        } else {
            LoginUser loginUser = new LoginUser();
            loginUser.setLoginId(user.getUid());
            loginUser.setUsername(loginBody.getUsername());
            StpUtil.login(user.getUid());
            System.out.println("tokenValue"+StpUtil.getTokenValue());
            System.out.println(StpUtil.getLoginId()+"userID");
            ajax.put("userId", user.getUid());
            ajax.put("userName", user.getUserName());
            ajax.put("token", StpUtil.getTokenValue());
            return R.ok(ajax);
        }
    }

    // 打印当前token信息， 浏览器访问： http://localhost:8081/test/tokenInfo
    @GetMapping("info")
    public AjaxJson tokenInfo() {
        System.out.println("======================= 进入方法，打印当前token信息 ========================= ");
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
        System.out.println(tokenInfo);
        return AjaxJson.getSuccessData(tokenInfo);
    }


    // 测试会话session接口， 浏览器访问： http://localhost:8081/test/session
    @RequestMapping("session")
    public AjaxJson session() throws JsonProcessingException {
        System.out.println("======================= 进入方法，测试会话session接口 ========================= ");
        System.out.println("当前是否登录：" + StpUtil.isLogin());
        System.out.println("当前登录账号session的id" + StpUtil.getSession().getId());
        System.out.println("当前登录账号session的id" + StpUtil.getSession().getId());
        System.out.println("测试取值name：" + StpUtil.getSession().get("name"));
        StpUtil.getSession().set("name", new Date());    // 写入一个值
        System.out.println("测试取值name：" + StpUtil.getSession().get("name"));
        System.out.println(new ObjectMapper().writeValueAsString(StpUtil.getSession()));
        return AjaxJson.getSuccess();
    }


    // 测试   浏览器访问： http://localhost:8081/test/test
    @RequestMapping("test")
    @SaCheckLogin
    public AjaxJson test() {
        System.out.println();
        System.out.println("--------------进入请求--------------");
        System.out.println(StpUtil.getExtra("username"));
        System.out.println(StpUtil.getExtra("nick"));
        return AjaxJson.getSuccess();
    }

    public static void loginByDevice(LoginUser loginUser, DeviceType deviceType) {
        SaHolder.getStorage().set("loginUser", loginUser);
        StpUtil.login(loginUser.getLoginId(), deviceType.getDevice());
        setLoginUser(loginUser);
    }

    public static void setLoginUser(LoginUser loginUser) {
        StpUtil.getTokenSession().set("loginUser", loginUser);
    }
}
