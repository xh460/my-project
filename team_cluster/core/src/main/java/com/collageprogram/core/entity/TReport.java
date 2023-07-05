package com.collageprogram.core.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author binggoCoder
 * @since 2023-06-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_report")

public class TReport implements Serializable {

    private static final long serialVersionUID = 1L;


    @TableId(value = "uid", type = IdType.ASSIGN_UUID)
    private String uid;


    private String teamuid;


    private Integer participants;


    private Integer fund;


    private String beginDate;


    private String endDate;


}
