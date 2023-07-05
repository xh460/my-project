package com.collageprogram.core.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author binggoCoder
 * @since 2023-06-29
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_team")

public class TTeam implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "uid", type = IdType.ASSIGN_UUID)
    private String uid;


    private String userid;


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
