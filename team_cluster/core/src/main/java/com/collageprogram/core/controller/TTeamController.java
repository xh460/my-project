package com.collageprogram.core.controller;


import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.collageprogram.common.entity.R;
import com.collageprogram.core.entity.*;
import com.collageprogram.core.entity.vo.TeamUser;
import com.collageprogram.core.entity.vo.TeamUserVos;
import com.collageprogram.core.mapper.TReportMapper;
import com.collageprogram.core.mapper.TTeamMapper;
import com.collageprogram.core.service.ITReportService;
import com.collageprogram.core.service.ITTeamService;
import com.collageprogram.core.service.ITUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author binggoCoder
 * @since 2023-06-29
 */
@SaIgnore

@RestController
@RequestMapping("/core/team")
public class TTeamController {
    @Autowired
    ITUserService itUserService;
    @Autowired
    ITTeamService itTeamService;
    @Autowired
    TTeamMapper tTeamMapper;
    @Autowired
    TReportMapper tReportMapperl;

    /**
     * 加入团队
     * @param teamid
     * @return
     */
    @GetMapping("/confirmteam")
    public R configfirmteam(@RequestParam("teamid") String teamid) {
        String loginId = String.valueOf(StpUtil.getLoginId());
        TUser uid = itUserService.getOne(new QueryWrapper<TUser>().eq("uid", loginId));

        String username = uid.getUserName();
        TTeam uid1 = itTeamService.getOne(new QueryWrapper<TTeam>().eq("uid", teamid));
        String money = uid1.getMoney();

        System.out.println("增加乘客~~~");
        tTeamMapper.saveTeamUser(username, teamid, money);
        return R.ok();
    }


    //我的团队
    @GetMapping("/getMyTeam")
    public R getMyTeam() {
        TUser uid = itUserService.getOne(new QueryWrapper<TUser>().eq("uid", String.valueOf(StpUtil.getLoginId())));
        List<actDetail> list1 = tTeamMapper.getMyTeamByUserName(uid.getUserName());
        System.out.println("getMyTeam");
//        List<TTeam> list = tTeamMapper.getMyTeam(String.valueOf(StpUtil.getLoginId()));
        return R.ok(list1);
    }

    @GetMapping("/AA")
    public R AA(@RequestParam("teamid") String treamid, @RequestParam("money") String money) {
        //增加AA团费
        tTeamMapper.AA(treamid, money);
//        //是否修改应缴费用
//        tTeamMapper.addAAA(treamid,money);
        return R.ok();
    }

    /**
     *查询活动
     * @return
     */
    @GetMapping("/list")
    public R list() {
        System.out.println("team list");
        return R.ok(tTeamMapper.queryActDetail());
    }

    /**
     * 确认团队成立
     * @param treamid
     * @return
     */
    @GetMapping("/confirm")
    public R confim(@RequestParam("teamid") String treamid) {
        System.out.println("确认团队");
        tTeamMapper.confim(treamid);
        TTeam team = itTeamService.query().eq("uid", treamid).one();
        String msg = team.getContent() + "  团队已成立";
        List<TUser> teamUser = tTeamMapper.getTeamUser(treamid);
        for (TUser tUser : teamUser) {
            tTeamMapper.sendMsg(tUser.getUserName(), msg);
        }
        return R.ok();
    }

    /**
     * 创建活动接口
     * @param tTeam
     * @return
     */
    @PostMapping("/add")
    public R add(@RequestBody TTeam tTeam) {
        //添加活动
        tTeam.setUserid(String.valueOf(StpUtil.getLoginId()));
        tTeam.setStatus("0");
        System.out.println("保存~~~");
        //将组长输入进去
        itTeamService.save(tTeam);

        //添加团长
        QueryWrapper<TTeam> tTeamQueryWrapper = new QueryWrapper<>();
        QueryWrapper<TTeam> userid = tTeamQueryWrapper.eq("userid", StpUtil.getLoginId());
        QueryWrapper<TTeam> beginTime = userid.eq("begin_time", tTeam.getBeginTime());
        TUser uid = itUserService.getOne(new QueryWrapper<TUser>().eq("uid", String.valueOf(StpUtil.getLoginId())));

        TTeam one = itTeamService.getOne(beginTime);
        tTeamMapper.saveTeamUser(uid.getUserName(), one.getUid(), one.getMoney());

        return R.ok("添加成功");
    }

    @PostMapping("/update")
    public R update(TTeam tTeam) {
        System.out.println("更新~~~");
        itTeamService.updateById(tTeam);
        return R.ok("更新成功");
    }



    @PostMapping("/saveTeamUser")
    public R saveInfo(@RequestBody TeamUserVos teamUserVos) {
        System.out.println("增加乘客~~~");
        tTeamMapper.saveBatchTeamUser(teamUserVos.getUsername(), teamUserVos.getTeamid());
        return R.ok();
    }

    @GetMapping("/saveTeamUseralone")
    public R saveInfoa(@RequestParam("teamid") String teamid, @RequestParam("username") String username) {
        //拿到应的钱数
        TTeam uid = itTeamService.getOne(new QueryWrapper<TTeam>().eq("uid", teamid));
        String money = uid.getMoney();

        System.out.println("增加乘客~~~");
        tTeamMapper.saveTeamUser(username, teamid, money);
        return R.ok();
    }

    /**
     * 追加金额
     * @param money
     * @param teamid
     * @param username
     */
    @GetMapping("changeMoney")
    public void changeMoney(@RequestParam("money") String money, @RequestParam("teamid") String teamid, @RequestParam("username") String username) {
        tTeamMapper.addMoney(teamid, money, username);
    }

    @GetMapping("/payMoney")
    public void payMoney(@RequestParam("money") String money, @RequestParam("teamid") String teamid) {
        TUser uid = itUserService.getOne(new QueryWrapper<TUser>().eq("uid", String.valueOf(StpUtil.getLoginId())));

        tTeamMapper.payMoney(teamid, money, uid.getUserName());
    }

    @GetMapping("/getTeamInfo")
    public R getTeamInfo(@RequestParam("teamid") String teamid) {
        System.out.println("获取团队信息~~~" + teamid);
        return R.ok(tTeamMapper.getTeamUser(teamid));
    }

    @GetMapping("/reportList")
    public R reportList() {
        return R.ok(tReportMapperl.selectList(null));
    }

    /**
     * 结束并生成报告
     * @param treamid
     * @return
     */
    @GetMapping("/doReport")
    public R report(@RequestParam("teamid") String treamid) {
//        TTeam teamid1 = itTeamService.getOne(new QueryWrapper<TTeam>().eq("teamid", treamid));

        //参会人数
        Integer count = tTeamMapper.selectCount(treamid);
        //所有资金
        Integer money = tTeamMapper.selectFunds(treamid);
        TTeam teamid = itTeamService.getOne(new QueryWrapper<TTeam>().eq("uid", treamid));
        String beginTime = teamid.getBeginTime();
        TReport tReport = new TReport();
        tReport.setBeginDate(beginTime);
        String format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        tReport.setEndDate(format);
        tReport.setFund(money);
        tReport.setTeamuid(treamid);
        tReport.setParticipants(count);
        tReportMapperl.insert(tReport);
        List<TReport> tReports = tReportMapperl.selectList(new QueryWrapper<>());
        tReportMapperl.changeCon1(treamid);
        return R.ok(tReports);
    }
}



