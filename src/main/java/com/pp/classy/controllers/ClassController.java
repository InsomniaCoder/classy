package com.pp.classy.controllers;

import com.pp.classy.services.AttendancesService;
import com.pp.classy.services.QRGeneratorService;
import java.awt.image.BufferedImage;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClassController {

  final QRGeneratorService qrGeneratorService;
  final AttendancesService attendancesService;

  public ClassController(
      QRGeneratorService qrGeneratorService, AttendancesService attendancesService) {
    this.qrGeneratorService = qrGeneratorService;
    this.attendancesService = attendancesService;
  }

  /**
   * For Teacher, Get All classes he/she created based on user id
   */
  @GetMapping
  public void getAllClasses() {
  }

  /**
   * For Teacher, Create a classroom given user id
   */
  @PostMapping
  public void createClass() {

  }

  /**
   * For Teacher, Generate a QR code for students to attend QR code is basically created by provide
   * a link to attendances URL
   *
   * @return generated image
   */
  @GetMapping(produces = MediaType.IMAGE_PNG_VALUE, path = "/{classId}/qr")
  public BufferedImage generateQR(@PathVariable Long classId, HttpServletRequest request)
      throws Exception {
    String currentRequestURL = request.getRequestURL().toString();
    String generatedUrl =
        currentRequestURL.substring(0, currentRequestURL.indexOf("/", 8)) + "/" + classId
            + "/attendances";
    System.out.println(generatedUrl);
    return qrGeneratorService.generateQRCodeImage(
        generatedUrl);
  }


  /**
   * For Teacher, Generate XML/JSON/CSV list of students that attended given class
   */
  @GetMapping("/{classId}/attendances")
  public List<String> generateClassAttendance(@PathVariable Long classId) {
    return attendancesService.getClassAttendances(classId);
  }

  /**
   * For Student, Create an attendance record into database
   */
  @ResponseStatus(code = HttpStatus.OK)
  @PostMapping("/{classId}/attendances")
  public void createAttendance(@PathVariable Long classId, @RequestParam String telephoneNumber) {
    System.out.println(telephoneNumber + " has attended " + classId);
    attendancesService.createAttendance(classId, telephoneNumber);
  }

}
