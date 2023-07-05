package com.collageprogram.core.controller;


import com.collageprogram.common.entity.R;
import com.collageprogram.core.mapper.TReportMapper;
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
@RequestMapping("/t-report")
public class TReportController {
    @Autowired
    TReportMapper tReportMapper;

    @GetMapping("/list")
    public R list() {
        System.out.println("report list");
        return R.ok(tReportMapper.selectList(null));
    }
}
