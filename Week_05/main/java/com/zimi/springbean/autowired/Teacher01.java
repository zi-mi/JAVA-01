package com.zimi.springbean.autowired;

import com.zimi.springbean.beans.Student;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Autowired 按类型自动装配
 */
public class Teacher01 {
    @Autowired
    private Student student;
    public void say(){
        System.out.println(student.getName()+"，叫家长来一下。");
    }
}
