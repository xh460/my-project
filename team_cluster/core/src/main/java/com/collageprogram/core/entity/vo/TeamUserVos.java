package com.collageprogram.core.entity.vo;

import lombok.Data;

import java.util.ArrayList;

/**
 * @Auther: binggoCoder
 * @Date: 2023/6/30
 */
@Data
public class TeamUserVos {
    private String teamid;
    private ArrayList<String> username;
}
