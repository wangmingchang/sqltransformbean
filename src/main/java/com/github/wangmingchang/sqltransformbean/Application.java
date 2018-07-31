package com.github.wangmingchang.sqltransformbean;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
@MapperScan("com.github.wangmingchang.sqltransformbean.pojo.dao.*.Dao")
@SpringBootApplication
@EnableTransactionManagement 
@EnableAutoConfiguration
public class Application 
{
    public static void main( String[] args )
    {
       SpringApplication.run(Application.class, args);
    }
}
