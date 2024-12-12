package com.jung.todo.config;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@MapperScan(basePackages = "com.jung.todo.repository")
@Configuration
public class MyBatisConfig {
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        // 1. SqlSessionFactoryBean 객체 생성
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();

        // 2. DataSource 설정, 매퍼 파일 위치 설정
        sessionFactory.setDataSource(dataSource);
        Resource[] res = new PathMatchingResourcePatternResolver().getResources("classpath:/mapper/*.xml"); //  XML 매퍼 파일들의 위치를 찾아 배열로 저장
        sessionFactory.setMapperLocations(res);

        // mapper.xml 의 resultType 패키지 주소 생략
        sessionFactory.setTypeAliasesPackage("com.jung.todo.entity, com.jung.todo.dto");

        // mybatis 설정 파일 세팅
        sessionFactory.setConfigLocation(new PathMatchingResourcePatternResolver().getResource("classpath:mybatis/mybatis-config.xml"));

        // 3. 생성된 SqlSessionFactory 객체 반환
        return sessionFactory.getObject();
    }

    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/clush?useSSL=false&characterEncoding=UTF-8&serverTimezone=Asia/Seoul");
        dataSource.setUsername("jung");
        dataSource.setPassword("jung");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return dataSource;
    }
}