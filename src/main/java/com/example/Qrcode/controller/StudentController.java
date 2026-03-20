package com.example.Qrcode.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.Qrcode.model.Student;
import com.example.Qrcode.service.StudentService;
import com.example.Qrcode.util.QRcodeGenerator;

@Controller
public class StudentController {

    @Autowired
    private StudentService service;

    // 🟢 Home page
    @GetMapping("/")
    public String form() {
        return "form";
    }
@PostMapping("/save")
public String saveStudent(@RequestParam String name,
                         @RequestParam String rollNo,
                         @RequestParam String roomNo,
                         @RequestParam String phone,
                         @RequestParam MultipartFile photo,
                         Model model) {

    try {

        String uploadPath = System.getProperty("user.dir") + "/uploads/";
        File dir = new File(uploadPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fileName = System.currentTimeMillis() + "_" + photo.getOriginalFilename();
        File file = new File(uploadPath + fileName);
        photo.transferTo(file);

        Student s = new Student();
        s.setName(name);
        s.setRollNo(rollNo);
        s.setRoomNo(roomNo);
        s.setPhone(phone);
        s.setPhotoPath(fileName);

        service.save(s);

        // ✅ IMPORTANT FIX (LIVE URL)
        String baseUrl = "https://thorough-peace-production-c59d.up.railway.app/";

        String qrText = baseUrl + "/room/" + roomNo;

        String qrFileName = "QR_" + roomNo + ".png";
        String qrPath = uploadPath + qrFileName;

        QRcodeGenerator.generateQR(qrText, qrPath);

        model.addAttribute("qr", "/uploads/" + qrFileName);

    } catch (Exception e) {
        e.printStackTrace();
    }

    return "result";
}