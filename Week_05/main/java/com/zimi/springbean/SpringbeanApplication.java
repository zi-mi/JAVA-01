package com.zimi.springbean;

import com.zimi.springbean.xml.Teacher;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringbeanApplication {

//    public static void main(String[] args) {
//        SpringApplication.run(SpringbeanApplication.class, args);
//
//    }

    public static void main(String[] args) {
        ClassPathXmlApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        Teacher teacher=applicationContext.getBean("teacher", Teacher.class);
        teacher.say();
    }
}
