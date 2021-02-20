package com.zimi.springbean.beans;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class Klass {
    
    List<Student> students;

    public Klass(Student student) {
    }

    public void dong(){
        System.out.println(this.getStudents());
    }

}
