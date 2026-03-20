package com.example.Qrcode.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Qrcode.model.Student;
import com.example.Qrcode.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repo;

    public Student save(Student s) {
        return repo.save(s);
    }

    public Student getByRoom(String roomNo) {
        return repo.findByRoomNo(roomNo);
    }
}