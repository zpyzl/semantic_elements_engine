/*
 * Copyright(c)2018-2020, 赵鹏阳
 * 项目名称:Yearn 文件名称:YearnApplication.java
 * Date:2020/1/1 下午9:21
 * Author: 赵鹏阳
 */

package com.zpy.yearn;

import com.zpy.yearn.common.YearnException;
import com.zpy.yearn.parser.service.StanfordParserService;
import com.zpy.yearn.techFacilities.repository.DictInitializer;
import com.zpy.yearn.utils.SpringUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.caucho.HessianProxyFactoryBean;


@SpringBootApplication
public class YearnApplication {

    public static void main(String[] args) throws YearnException {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(YearnApplication.class, args);

        SpringUtil.setApplicationContext(applicationContext);
        DictInitializer.initDict();
    }
    @Bean
    public HessianProxyFactoryBean initStanfordParserServiceClient() {
        HessianProxyFactoryBean factory = new HessianProxyFactoryBean();
        factory.setServiceUrl("http://localhost:8081/StanfordParserService");
        factory.setServiceInterface(StanfordParserService.class);
        return factory;
    }

}
