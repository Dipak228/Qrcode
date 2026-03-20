package com.example.Qrcode.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Qrcode.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByRoomNo(String roomNo);
}