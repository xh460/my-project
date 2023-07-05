package com.collageprogram.core.mapper;

import com.collageprogram.core.entity.TTeam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.collageprogram.core.entity.TUser;
import com.collageprogram.core.entity.actDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author binggoCoder
 * @since 2023-06-29
 */
public interface TTeamMapper extends BaseMapper<TTeam> {
    //保存成员
    void saveTeamUser(@Param("username") String username, @Param("teamid") String teamid,@Param("money") String  money);
    //批量保存团员
    void saveBatchTeamUser(@Param("username") List<String>username, @Param("teamid") String teamid);
    //获取团队成员
    List<TUser> getTeamUser(@Param("teamid") String teamid);
    //通知小组成员小组成立,
    void noticeTeamUser(@Param("teamid") String teamid);
    void noticeTeamUserMsg(@Param("teamid") String teamid, @Param("msg") String msg, @Param("username") String username);
    //缴纳资金
    void payMoney(@Param("teamid") String teamid, @Param("money") String money, @Param("username") String username);
    //修改团队AA资金
    void updateTeamMoney(@Param("teamid") String teamid, @Param("money") String money);
    //追加某人资金
    void addMoney(@Param("teamid") String teamid, @Param("money") String money, @Param("username") String username);
    //获取团队成员缴费情况
    List<TUser> getTeamUserPay(@Param("teamid") String teamid, @Param("username") String username);

    List<TTeam> getMyTeam(String userid);

    void confim(String treamid);

    void sendMsg(@Param("userName") String userName, @Param("msg") String msg);

    Integer selectCount(String treamid);

    Integer selectFunds(String treamid);

    void AA(@Param("treamid") String treamid, @Param("money") String money);


    void addAAA(String treamid, String money);

    List<actDetail> getMyTeamByUserName(String userName);


    List<actDetail> queryActDetail();
}
