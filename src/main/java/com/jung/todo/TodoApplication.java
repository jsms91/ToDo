package com.jung.todo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
@MapperScan(basePackages = "com.jung.todo.repository")
public class TodoApplication {//implements CommandLineRunner {
//	@Autowired
//	private ApplicationContext ac;

	public static void main(String[] args)  {
		SpringApplication.run(TodoApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		System.out.println("빈 정렬 시작 =====================");
//		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
//		Arrays.sort(beanDefinitionNames);
//		for (String beanDefinitionName : beanDefinitionNames) {
//			System.out.println("beanDefinitionName = " + beanDefinitionName);
//
//		}
//	}
}