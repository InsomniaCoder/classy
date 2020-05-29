package com.pp.classy.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClassController {


  /**
   * For Teacher,
   * Get All classes he/she created based on user id
   */
  @GetMapping
  public void getAllClasses() {
  }

  /**
   * For Teacher,
   * Create a classroom given user id
   */
  @PostMapping
  public void createClass() {

  }

  /**
   * For Teacher,
   * Generate a QR code for students to attend
   * QR code is basically created by provide a link to attendances URL
   */
  @GetMapping("/{classId}/qr")
  public void generateQR(@PathVariable Long classId) {

  }


  /**
   * For Teacher,
   * Generate XML/JSON/CSV list of students that attended given class
   */
  @GetMapping("/{classId}/attendances")
  public void generateClassAttendance(@PathVariable Long classId) {

  }

  /**
   * For Student,
   * Create an attendance record into database
   */
  @PostMapping("/{classId}/students/{studentId}")
  public void createAttendance(@PathVariable Long classId, @PathVariable Long studentId) {

  }

}
