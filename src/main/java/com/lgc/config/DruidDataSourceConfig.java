package com.lgc.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @program: javaConfig-ssm
 * @description: dataSource config
 * @author: lgc
 * @create: 2019-06-01 17:14
 **/
@Configuration
@ComponentScan(basePackages = "com.lgc.dao")
@PropertySource("classpath:/jdbc.properties")
public class DruidDataSourceConfig {


    private final static Logger LOG = Logger.getLogger(DruidDataSourceConfig.class);

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;

    @Value("${spring.datasource.initialSize}")
    private int initialSize;

    @Value("${spring.datasource.minIdle}")
    private int minIdle;

    @Value("${spring.datasource.maxActive}")
    private int maxActive;

    @Value("${spring.datasource.maxWait}")
    private int maxWait;

    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;

    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.testOnReturn}")
    private boolean testOnReturn;

    @Value("${spring.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;

    @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    @Value("${spring.datasource.filters}")
    private String filters;

    @Value("{spring.datasource.connectionProperties}")
    private String connectionProperties;

    @Bean     //声明其为Bean实例
    public DataSource dataSource(){
        LOG.info("Initialize the data source...");
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(this.dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);

        //configuration
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            LOG.error("druid configuration initialization filter", e);
        }
        datasource.setConnectionProperties(connectionProperties);
        return datasource;
    }

    /*
    //JdbcTemplate的配置
    @Bean
    public JdbcTemplate jdbcTemplate(){
    	JdbcTemplate jdbcTemplate = new JdbcTemplate();
    	jdbcTemplate.setDataSource(dataSource());
    	return jdbcTemplate;
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(){
    	NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource());
    	return namedParameterJdbcTemplate;
    }*/

    //全局配置
    public GlobalConfiguration globalConfigurationBean(){
        GlobalConfiguration globalConfiguration = new GlobalConfiguration();
        //AUTO->`0`("数据库ID自增")、INPUT->`1`(用户输入ID")、ID_WORKER->`2`("全局唯一ID")、UUID->`3`("全局唯一ID")
        globalConfiguration.setIdType(2);
        //全局表为下划线命名设置 true
        globalConfiguration.setDbColumnUnderline(true);
        return globalConfiguration;
    }

    //mybatis的配置
    @Bean
    public MybatisSqlSessionFactoryBean sqlSessionFactoryBean() throws IOException{
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        //SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //mybatis-plus插件类
        MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        //数据源
        sqlSessionFactoryBean.setDataSource(dataSource());
        //MP全局注入
        sqlSessionFactoryBean.setGlobalConfig(globalConfigurationBean());
        sqlSessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources("classpath*:mappers/*.xml"));
        //别名，让*Mpper.xml实体类映射可以不加上具体包名
        sqlSessionFactoryBean.setTypeAliasesPackage("com.lgc.model");
        return sqlSessionFactoryBean;
    }

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager dataSourceTransactionManager(){
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource());
        return dataSourceTransactionManager;
    }

    @Bean(name="transactionInterceptor")
    public TransactionInterceptor interceptor(){
        TransactionInterceptor interceptor = new TransactionInterceptor();
        interceptor.setTransactionManager(dataSourceTransactionManager());

        Properties transactionAttributes = new Properties();
        transactionAttributes.setProperty("save*", "PROPAGATION_REQUIRED");
        transactionAttributes.setProperty("del*", "PROPAGATION_REQUIRED");
        transactionAttributes.setProperty("update*", "PROPAGATION_REQUIRED");
        transactionAttributes.setProperty("get*", "PROPAGATION_REQUIRED,readOnly");
        transactionAttributes.setProperty("find*", "PROPAGATION_REQUIRED,readOnly");
        transactionAttributes.setProperty("*", "PROPAGATION_REQUIRED");

        interceptor.setTransactionAttributes(transactionAttributes);
        return interceptor;
    }

    /*
    //放这里会导致@value注解获取不到配置的值，移到RootConfig
    @Bean
    public BeanNameAutoProxyCreator proxycreate(){
    	BeanNameAutoProxyCreator proxycreate = new BeanNameAutoProxyCreator();
    	proxycreate.setProxyTargetClass(true);
    	proxycreate.setBeanNames("*ServiceImpl");
    	proxycreate.setInterceptorNames("transactionInterceptor");
    	return proxycreate;
    }*/
}