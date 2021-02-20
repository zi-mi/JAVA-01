package com.zimi.springbean;

import com.zimi.springbean.beans.Klass;
import com.zimi.springbean.beans.School;
import com.zimi.springbean.beans.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpringbeanApplication {



    public static void main(String[] args) {


        Student lisi = new Student();
        lisi.setId(1);
        lisi.setName("lisi");

        Student student100 = new Student();
        student100.setId(12);
        student100.setName("wangwu");

        List<Student> s = new ArrayList<>();
        s.add(lisi);
        s.add(student100);
        Klass class1 = new Klass(student100);
        class1.setStudents(s);

        School school = new School();
        school.setClass1(class1);
        school.setStudent100(lisi);
        school.ding();

        SpringApplication.run(SpringbeanApplication.class, args);
    }

}
