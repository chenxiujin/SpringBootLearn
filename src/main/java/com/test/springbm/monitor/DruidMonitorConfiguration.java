package com.test.springbm.monitor;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.test.springbm.config.impl.DruidConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @version 1.0
 * @author CXP
 * @date 2019/4/11 18:37
 */
@Configuration
@PropertySource(value = "classpath:config/druid-monitor.properties")
@EnableConfigurationProperties({DruidConfig.class})
public class DruidMonitorConfiguration {

    @Value("${spring.datasource.druid.web-stat-filter.enabled}")
    private String webStatFilterEnabled;
    @Value("${druid.monitor.allow}")
    private String allow;
    @Value("${druid.monitor.deny}")
    private String deny;
    @Value("${druid.monitor.loginUsername}")
    private String loginUsername;
    @Value("${druid.monitor.loginPassword}")
    private String loginPassword;
    @Value("${druid.monitor.resetEnable}")
    private String resetEnable;

    /**
     * 注册Servlet信息，配置监控视图
     * @return
     */
    @Bean
    public ServletRegistrationBean druidStatViewServlet() {
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        servletRegistrationBean.addInitParameter("spring.datasource.druid.web-stat-filter.enabled",this.webStatFilterEnabled);
        servletRegistrationBean.addInitParameter("allow", this.allow);
        servletRegistrationBean.addInitParameter("deny", this.deny);
        servletRegistrationBean.addInitParameter("loginUsername", this.loginUsername);
        servletRegistrationBean.addInitParameter("loginPassword", this.loginPassword);
        servletRegistrationBean.addInitParameter("resetEnable", this.resetEnable);
        return servletRegistrationBean;
    }

    /**
     * 注册Filter信息，监控拦截器
     * @return
     */
    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //拦截url类型，*表示拦截所有请求
        filterRegistrationBean.addUrlPatterns("/*");
        //忽略资源
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        return filterRegistrationBean;
    }
}
