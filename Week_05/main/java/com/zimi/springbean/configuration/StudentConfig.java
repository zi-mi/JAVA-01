package com.zimi.springbean.configuration;

import com.zimi.springbean.beans.Klass;
import com.zimi.springbean.beans.Student;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 在Java中装配Bean
 */
@Configuration
public class StudentConfig {

    // 创建一个Bean
    @Bean
    public Student setStudent(){
        return new Student(12,"lisi");
    }

    // Spring自动注入
    @Bean
    public Klass setKlass(Student student){
        return new Klass(student);
    }


}
