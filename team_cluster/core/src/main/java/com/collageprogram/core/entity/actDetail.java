package com.collageprogram.core.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author zengwei
 * @data - 17:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class actDetail {
    private static final long serialVersionUID = 1L;


    private String uid;


    private String userName;


    private String beginTime;


    private String beginPlace;


    private String content;


    private String money;


    private String status;
    private String con;

    @TableField(exist = false)
    private List<TUser> users;
    @TableField(exist = false)
    private String pay;
    @TableField(exist = false)
    private String fund;
}
