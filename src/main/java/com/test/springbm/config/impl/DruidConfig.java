package com.test.springbm.config.impl;

/**
 *                             _ooOoo_
 *                            o8888888o
 *                            88" . "88
 *                            (| -_- |)
 *                            O\  =  /O
 *                         ____/`---'\____
 *                       .'  \\|     |//  `.
 *                      /  \\|||  :  |||//  \
 *                     /  _||||| -:- |||||-  \
 *                     |   | \\\  -  /// |   |
 *                     | \_|  ''\---/''  |   |
 *                     \  .-\__  `-`  ___/-. /
 *                   ___`. .'  /--.--\  `. . __
 *                ."" '<  `.___\_<|>_/___.'  >'"".
 *               | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 *               \  \ `-.   \_ __\ /__ _/   .-` /  /
 *          ======`-.____`-.___\_____/___.-`____.-'======
 *                             `=---='
 *          ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 *                     佛祖保佑        永无BUG
 *            佛曰:
 *                   写字楼里写字间，写字间里程序员；
 *                   程序人员写程序，又拿程序换酒钱。
 *                   酒醒只在网上坐，酒醉还来网下眠；
 *                   酒醉酒醒日复日，网上网下年复年。
 *                   但愿老死电脑间，不愿鞠躬老板前；
 *                   奔驰宝马贵者趣，公交自行程序员。
 *                   别人笑我忒疯癫，我笑自己命太贱；
 *                   不见满街漂亮妹，哪个归得程序员？
 */

import com.alibaba.druid.pool.DruidDataSource;
import com.test.springbm.config.DatabaseConfig;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author CXP
 * @date 2019/3/27 20:18
 * @version 1.0
 */
@Configuration
@EnableTransactionManagement
@ConfigurationProperties(value="application.properties")
@MapperScan(basePackages = DruidConfig.PACKAGE, sqlSessionFactoryRef = "sessionFactory")
public class DruidConfig implements DatabaseConfig {


    public static final String PACKAGE = "com.test.springbm.mapper";

    public static final String MAPPER = "classpath:/mapper/*Mapper.xml";

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

    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;

    /**
     * 指明是否在从池中取出连接前进行检验,如果检验失败,则从池中去除连接并尝试取出另一个.<br/>
     * 注意: 设置为true后如果要生效,validationQuery参数必须设置为非空字符串
     */
    @Value("${spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;

    /**
     * 指明是否在归还到池中前进行检验<br/>
     * 注意: 设置为true后如果要生效,validationQuery参数必须设置为非空字符串
     */
    @Value("${spring.datasource.testOnReturn}")
    private boolean testOnReturn;

    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    /**
     * 当开启时, 将为每个连接创建一个statement池,并且被方法创建的PreparedStatements将被缓存起来:
     */
    @Value("${spring.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;

    /**
     * 不限制  statement池能够同时分配的打开的statements的最大数量,如果设置为0表示不限制
     */
    @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;

    /*@Value("${spring.datasource.defaultAutoCommit:false}")
    private boolean defaultAutoCommit;*/

    @Value("${spring.datasource.filters}")
    private String filters;

    /**
     * 当建立新连接时被发送给JDBC驱动的连接参数
     */
    @Value("${spring.datasource.connectionProperties}")
    private String connectionProperties;

    /**
     * 定义数据源
     * 注意@Primary注解表示：自动装配时当出现多个Bean候选者时，被注解为@Primary的Bean将作为首选者，否则将抛出异常。SpringBoot为我们提供
     * 了一个默认的dataSource，当我们需要用我们自己的连接池的时候可以使用@primary注解告诉SpringBoot优先使用自己的dataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "dataSource")
    @Primary
    @Override
    public DataSource dataSource() throws Exception {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(this.dbUrl);
        datasource.setUsername(this.username);
        datasource.setPassword(this.password);
        datasource.setDriverClassName(this.driverClassName);
        datasource.setInitialSize(this.initialSize);
        datasource.setMinIdle(this.minIdle);
        datasource.setMaxActive(this.maxActive);
        datasource.setMaxWait(this.maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(this.timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(this.minEvictableIdleTimeMillis);
        datasource.setValidationQuery(this.validationQuery);
        datasource.setTestWhileIdle(this.testWhileIdle);
        datasource.setTestOnBorrow(this.testOnBorrow);
        datasource.setTestOnReturn(this.testOnReturn);
        datasource.setPoolPreparedStatements(this.poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(this.maxPoolPreparedStatementPerConnectionSize);
        /*datasource.setDefaultAutoCommit(this.defaultAutoCommit);*/
        datasource.setFilters(this.filters);
        datasource.setConnectionProperties(this.connectionProperties);
        return datasource;
    }

    /**
     * 定义session工厂
     * 注：ualifier的意思是合格者，通过这个标示，表明了哪个实现类才是我们所需要的，
     * 我们修改调用代码，添加@Qualifier注解，需要注意的是@Qualifier的参数名称必须为我们之前定义@Service注解的名称之一！
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "sessionFactory")
    @Primary
    @Override
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResources(DruidConfig.MAPPER));
        return sessionFactory.getObject();
    }

    /**
     * 定义事务管理器
     * @param dataSource
     * @return
     */
    @Bean(name = "transactionManager")
    @Override
    public DataSourceTransactionManager dataSourceTransactionManager(@Qualifier("dataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

}