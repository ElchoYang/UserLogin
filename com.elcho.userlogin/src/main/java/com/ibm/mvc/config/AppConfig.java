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
 * Spring ������
 * ͨ����̵ķ�ʽ������ DataSource Bean, SessionFactory Bean, �Լ� PlatformTransactionManager Bean
 */

@Configuration
@ComponentScan({"com.ibm.mvc.dao.impl", "com.ibm.mvc.service.impl"})
@PropertySource("classpath:db.properties")
@EnableAspectJAutoProxy  //����AOP
@EnableTransactionManagement //��������ע��� ����ʽ����
//@MapperScan(value = "com.ibm.mvc.dao.impl")  //�Զ�ɨ��
public class AppConfig {

    //��־��Ϣ
    private static final Logger LOGGER = Logger.getLogger(AppConfig.class);

    public AppConfig( ) {

        LOGGER.debug("---- ��ʼ�� AppConfig ʵ��....");

        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:elcho");
        //
        JdbcTemplate template = new JdbcTemplate(dataSource);

        String sql = "drop table  if exists USER_INFO;";
        template.execute(sql);
        sql = "create table TBL_USER(ID INTEGER  auto_increment unique, NAME CHAR, ROLE CHAR, email CHAR, USER_NAME CHAR,PASSWORD CHAR);";
        template.execute(sql);
        sql = "insert into TBL_USER(name,role,email, USER_NAME,PASSWORD) values('admin','admin','admin@ibm.com','admin','admin')";
        template.update(sql);
    }

    @Autowired   //ͨ����ȡ @PropertySource ע�� ָ���������ļ����õ���Ϣ
    private Environment env;

    @Bean
    public DataSource getDataSource() {
        LOGGER.debug("--- ����BasicDataSource ����.... ");
        DruidDataSource dataSource = new DruidDataSource();
        //�����Ը�ֵ
        LOGGER.debug("--- ������Դ��������Ը�ֵ....");
        dataSource.setDriverClassName(env.getProperty("driver"));
        LOGGER.info("*** �����ࣺ"+env.getProperty("driver"));
        dataSource.setUrl(env.getProperty("url"));
        LOGGER.info("*** URL��"+env.getProperty("url"));
       /* dataSource.setUsername(env.getProperty("user_name"));
        LOGGER.info("*** �û�����"+env.getProperty("user_name"));
        dataSource.setPassword(env.getProperty("password"));
        LOGGER.info("*** ���룺"+env.getProperty("password"));*/
        //
        String initialSize = env.getProperty("initialSize");
        if(initialSize != null) {
            dataSource.setInitialSize(Integer.parseInt(initialSize));
            LOGGER.debug("--- �������ӳصĳ�ʼ��С: "+initialSize);
        }
        //����
        return dataSource;
    }

    //�����mybatis������ص����� �����������ã������κ�mybatis���õ�xml��
/*    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(@Autowired DataSource dataSource) {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        //��������Դ
        factoryBean.setDataSource(dataSource);
        //���� ʵ��ı���
        factoryBean.setTypeAliasesPackage("com.ibm.mvc.entity");
        //����
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
