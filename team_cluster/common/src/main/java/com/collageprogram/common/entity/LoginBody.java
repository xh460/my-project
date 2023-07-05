package com.collageprogram.common.entity;

import lombok.Data;

/**
 * @Auther: binggoCoder
 * @Date: 2023/6/29
 */
@Data
public class LoginBody {
    private String username;
    private String password;
    private String uuid;
}
