package com.collageprogram.core.service.Impl;

import com.collageprogram.core.entity.TTeam;
import com.collageprogram.core.mapper.TTeamMapper;
import com.collageprogram.core.service.ITTeamService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author binggoCoder
 * @since 2023-06-29
 */
@Service
public class TTeamServiceImpl extends ServiceImpl<TTeamMapper, TTeam> implements ITTeamService {

    @Autowired
    TTeamMapper tTeamMapper;
//    @Override
//    public void saveTeamUser(String username, String teamid) {
//        tTeamMapper.saveTeamUser(username,teamid);
//    }
}
