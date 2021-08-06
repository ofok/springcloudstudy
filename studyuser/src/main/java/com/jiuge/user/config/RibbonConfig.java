package com.jiuge.user.config;

import com.alibaba.cloud.nacos.ribbon.NacosRule;
import com.jiuge.user.rule.NacosRandomWithWeightRule;
import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huwei
 * @version 1.0
 * @date 2021/8/4 10:24
 */
@Configuration
public class RibbonConfig {
    /**
     * 全局配置
     * 指定负载均衡策略
     * @return
     */
   /*
    @Bean
    public NacosRule IRule(){
        // 指定使用Nacos提供的负载均衡策略（优先调用同一集群的实例，基于随机权重）
        return new NacosRule();
    }
    */

   @Bean
   public IRule ribbonRule(){
        return new NacosRandomWithWeightRule();
   }
}
