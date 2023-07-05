//package com.collageprogram.core.enums;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import org.apache.commons.lang.StringUtils;
//
///**
//
// */
//@Getter
//@AllArgsConstructor
//public enum UserType {
//
//    /**
//     * pc端
//     */
//    SYS_USER("sys_user"),
//
//    /**
//     * app端
//     */
//    APP_USER("app_user");
//
//    private final String userType;
//
//    public static UserType getUserType(String str) {
//        for (UserType value : values()) {
//            if (StringUtils.contains(str, value.getUserType())) {
//                return value;
//            }
//        }
//        throw new RuntimeException("'UserType' not found By " + str);
//    }
//}
