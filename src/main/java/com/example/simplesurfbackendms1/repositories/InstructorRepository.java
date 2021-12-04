package com.example.simplesurfbackendms1.repositories;

import com.example.simplesurfbackendms1.models.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor,Long> {
}
