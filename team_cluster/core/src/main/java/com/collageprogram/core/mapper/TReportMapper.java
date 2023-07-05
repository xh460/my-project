package com.collageprogram.core.mapper;

import com.collageprogram.core.entity.TReport;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.collageprogram.core.entity.TTeam;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author binggoCoder
 * @since 2023-06-30
 */
public interface TReportMapper extends BaseMapper<TReport> {

    void changeCon(TTeam teamid);

    void changeCon1(String treamid);
}
