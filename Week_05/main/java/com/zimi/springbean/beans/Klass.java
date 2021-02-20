package com.zimi.springbean.beans;


import lombok.Data;

import java.util.List;

@Data
public class Klass { 
    
    List<Student> students;

    public Klass(Student student) {
    }

    public void dong(){
        System.out.println(this.getStudents());
    }

    public List<Student> getStudents() {
        return students;
    }

}
