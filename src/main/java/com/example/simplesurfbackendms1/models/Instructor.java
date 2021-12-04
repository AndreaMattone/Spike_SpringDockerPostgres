package com.example.simplesurfbackendms1.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String emailInstructor;

    public Instructor() {
    }
    public Instructor(Long id, String emailInstructor) {
        this.id = id;
        this.emailInstructor = emailInstructor;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmailInstructor() {
        return emailInstructor;
    }
    public void setEmailInstructor(String emailInstructor) {
        this.emailInstructor = emailInstructor;
    }
}
