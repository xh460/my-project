package com.collageprogram.core.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.filter.SaServletFilter;
import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.jwt.StpLogicJwtForSimple;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import com.collageprogram.core.properties.SecurityProperties;
import com.collageprogram.core.util.AjaxJson;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * [Sa-Token 权限认证] 配置类 
 * @author kong
 *
 */
//构造器注入
@RequiredArgsConstructor
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {
	//构造器注入
	private final SecurityProperties securityProperties ;
	/**
	 * 注册 Sa-Token 拦截器打开注解鉴权功能  
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 注册 Sa-Token 拦截器打开注解鉴权功能 
		registry.addInterceptor(new SaInterceptor(hander->{

					// 自定义注解 @Anonymous 匿名访问配置，后续版本将删除

					// 登录验证 -- 排除多个路径
					SaRouter
							// 获取所有的
							.match("/**")
							// 排除下不需要拦截的（每次匹配）
							.notMatch(securityProperties.getExcludes())
							// 对未排除的路径进行检查
							.check(() -> {
								// 检查是否登录 是否有token
								StpUtil.checkLogin();

								// 有效率影响 用于临时测试
								// if (log.isDebugEnabled()) {
								//     log.debug("剩余有效时间: {}", StpUtil.getTokenTimeout());
								//     log.debug("临时有效时间: {}", StpUtil.getTokenActivityTimeout());
								// }

							});

				})).addPathPatterns("/**")
				.excludePathPatterns(securityProperties.getExcludes());
	}

	@Bean
	public StpLogic getStpLogicJwt() {
		// Sa-Token 整合 jwt (简单模式)
		return new StpLogicJwtForSimple();
	}
    
}
