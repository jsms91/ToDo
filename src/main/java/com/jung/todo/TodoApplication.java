package com.jung.todo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling //자정마다 자동으로 코드 실행하기 위한 어노테이션(@Scheduled 사용하기 위해 선언)
//@MapperScan(basePackages = "com.jung.todo.repository")
public class TodoApplication {
	public static void main(String[] args)  {
		SpringApplication.run(TodoApplication.class, args);
	}
}

/* 모든 빈을 정렬하는 코드(빈등록확인)
public class TodoApplication implements CommandLineRunner {
	@Autowired
	private ApplicationContext ac;

	public static void main(String[] args)  {
		SpringApplication.run(TodoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("빈 정렬 시작 =====================");
		String[] beanDefinitionNames = ac.getBeanDefinitionNames();
		Arrays.sort(beanDefinitionNames);
		for (String beanDefinitionName : beanDefinitionNames) {
			System.out.println("beanDefinitionName = " + beanDefinitionName);

		}
	}
}*/