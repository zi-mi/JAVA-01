package com.zimi.springbean.xml;

import com.zimi.springbean.beans.Student;

/**
 * 在xml文件中配置Bean
 */
public class Teacher {

    private Student student;
    public void setStudent(Student student) {
        this.student = student;
    }
    public void say(){
        System.out.println(student.getName()+"，叫家长来一下。");
    }
}
