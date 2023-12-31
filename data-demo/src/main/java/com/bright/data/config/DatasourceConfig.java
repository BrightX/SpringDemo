package com.bright.data.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.support.JdbcTransactionManager;
import tk.mybatis.mapper.autoconfigure.MybatisProperties;
import tk.mybatis.mapper.autoconfigure.SpringBootVFS;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;

/**
 * 主要数据源
 */
@Configuration
@MapperScan(basePackages = "com.bright.data.mapper", sqlSessionFactoryRef = "db1SqlSessionFactory")
public class DatasourceConfig {
    @Bean("db1")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.db1")
    public DataSource buildDataSource() {
        return DataSourceBuilder.create()
            .type(DruidDataSource.class)
            .build();
    }

    /**
     * see org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration#sqlSessionFactory
     *
     * @see tk.mybatis.mapper.autoconfigure.MapperAutoConfiguration#sqlSessionFactory
     */
    @Bean("db1SqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("db1") DataSource dataSource,
                                               MybatisProperties mybatisProperties) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource);
        factory.setVfs(SpringBootVFS.class);
        factory.setConfiguration(new org.apache.ibatis.session.Configuration());
        factory.setConfigurationProperties(mybatisProperties.getConfigurationProperties());
        factory.setMapperLocations(mybatisProperties.resolveMapperLocations());
        factory.setTypeAliasesPackage(mybatisProperties.getTypeAliasesPackage());

        return factory.getObject();
    }

    @Bean("db1TransactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager(@Qualifier("db1") DataSource dataSource) {
        return new JdbcTransactionManager(dataSource);
    }
}
