package com.ibm.mvc.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * Created by yejf
 * Spring 配置类
 * 通过编程的方式来生成 DataSource Bean, SessionFactory Bean, 以及 PlatformTransactionManager Bean
 */

@Configuration
@ComponentScan({"com.ibm.mvc.dao.impl", "com.ibm.mvc.service.impl"})
@PropertySource("classpath:db.properties")
@EnableAspectJAutoProxy  //启动AOP
@EnableTransactionManagement //启动基于注解的 声明式事务
//@MapperScan(value = "com.ibm.mvc.dao.impl")  //自动扫描
public class AppConfig {

    //日志信息
    private static final Logger LOGGER = Logger.getLogger(AppConfig.class);

    public AppConfig( ) {

        LOGGER.info("---- 初始化 AppConfig 实例....");

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:elcho");
        //
        JdbcTemplate template = new JdbcTemplate(dataSource);

        String sql = "drop table  if exists TBL_USER;";
        template.execute(sql);
        sql = "create table TBL_USER(ID INTEGER  auto_increment unique, NAME CHAR, ROLE CHAR, email CHAR, USERNAME CHAR,PASSWORD CHAR);";
        template.execute(sql);
        sql = "insert into TBL_USER(name,role,email, USERNAME,PASSWORD) values('admin','admin','admin@ibm.com','admin','admin')";
        template.update(sql);

    }

    @Autowired   //通过获取 @PropertySource 注解 指定的属性文件来得到信息
    private Environment env;

    @Bean
    public DataSource getDataSource() {
        LOGGER.debug("--- 创建BasicDataSource 对象.... ");
        DruidDataSource dataSource = new DruidDataSource();
        //给属性赋值
        LOGGER.debug("--- 给数据源的相关属性赋值....");
        dataSource.setDriverClassName(env.getProperty("driver"));
        LOGGER.info("*** 驱动类："+env.getProperty("driver"));
        dataSource.setUrl(env.getProperty("url"));
        LOGGER.info("*** URL："+env.getProperty("url"));
       /* dataSource.setUsername(env.getProperty("user_name"));
        LOGGER.info("*** 用户名："+env.getProperty("user_name"));
        dataSource.setPassword(env.getProperty("password"));
        LOGGER.info("*** 密码："+env.getProperty("password"));*/
        //
        String initialSize = env.getProperty("initialSize");
        if(initialSize != null) {
            dataSource.setInitialSize(Integer.parseInt(initialSize));
            LOGGER.debug("--- 设置连接池的初始大小: "+initialSize);
        }
        //返回
        return dataSource;
    }

    //添加与mybatis集成相关的配置 【纯代码配置，无需任何mybatis配置的xml】
/*    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(@Autowired DataSource dataSource) {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        //设置数据源
        factoryBean.setDataSource(dataSource);
        //设置 实体的别名
        factoryBean.setTypeAliasesPackage("com.ibm.mvc.entity");
        //返回
        return factoryBean;
    }*/


    @Bean
    public JdbcTemplate template(@Autowired DataSource dataSource) {
        //
        JdbcTemplate template = new JdbcTemplate(dataSource);
        //
        return template;
    }

    @Bean
    @Autowired
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        PlatformTransactionManager tx =
                new DataSourceTransactionManager(dataSource);
        //
        return tx;
    }
}
