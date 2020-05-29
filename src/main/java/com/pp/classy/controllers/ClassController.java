package com.pp.classy.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClassController {

  //teacher
  @GetMapping
  public void getAllClasses() {

  }

  //teacher
  @PostMapping
  public void createClass() {

  }

  //teacher
  @GetMapping("/{classId}/qr")
  public void generateQR(@PathVariable Long classId) {

  }


  //teacher
  @GetMapping("/{classId}/attendances")
  public void generateClassAttendance(@PathVariable Long classId) {

  }

  //student
  @PostMapping("/{classId}/students/{studentId}")
  public void createAttendance(@PathVariable Long classId, @PathVariable Long studentId) {

  }

}
