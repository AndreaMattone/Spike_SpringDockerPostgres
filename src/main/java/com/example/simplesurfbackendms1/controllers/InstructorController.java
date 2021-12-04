package com.example.simplesurfbackendms1.controllers;

import com.example.simplesurfbackendms1.models.Instructor;
import com.example.simplesurfbackendms1.repositories.InstructorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class InstructorController {

    @Autowired
    private InstructorRepository instructorRepository;

    @GetMapping("/instructors")
    public List<Instructor> list(){
        return instructorRepository.findAll();
    }

    @PostMapping("/instructors/create")
    @ResponseStatus(HttpStatus.OK)
    public void create(@RequestBody Instructor instructor){
        instructorRepository.save(instructor);
    }


}
