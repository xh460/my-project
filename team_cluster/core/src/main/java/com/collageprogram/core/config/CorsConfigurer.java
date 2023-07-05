package com.collageprogram.core.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @Description: 跨域访问控制
 * @author: wanjun
 */
@Configuration
public class CorsConfigurer
{

	@Value("${cross.domain}")
	private String crosDomain;

	@Bean
	public CorsFilter corsFilter()
	{
		// 跨域配置地址
		List<String> crosDomainList = Arrays.asList(crosDomain.split(","));

		CorsConfiguration corsConfiguration = new CorsConfiguration();
		// 1、允许来源
		corsConfiguration.setAllowedOrigins(crosDomainList);
		// 2、允许任何请求头
		corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
		// 3、允许任何方法
		corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
		// 4、允许凭证
		corsConfiguration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(source);
	}
}

