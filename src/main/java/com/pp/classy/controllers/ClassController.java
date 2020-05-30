package com.pp.classy.controllers;

import com.pp.classy.entities.Classes;
import com.pp.classy.exception.ClassesNotFoundException;
import com.pp.classy.exception.UsersNotFoundException;
import com.pp.classy.services.AttendancesService;
import com.pp.classy.services.ClassesService;
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
  final ClassesService classesService;

  public ClassController(
      QRGeneratorService qrGeneratorService, AttendancesService attendancesService,
      ClassesService classesService) {
    this.qrGeneratorService = qrGeneratorService;
    this.attendancesService = attendancesService;
    this.classesService = classesService;
  }

  /**
   * For Teacher, Get All classes he/she created based on user id
   * @param teacherId
   * @return
   * @throws UsersNotFoundException
   */
  @GetMapping("/teachers/{teacherId}/classes")
  public List<Classes> getAllClasses(@PathVariable Long teacherId) throws UsersNotFoundException {
    return classesService.getAllClasses(teacherId);
  }

  /**
   * For Teacher, Create a classroom given teacher id
   *
   * @param teacherId
   * @return generated class id
   */
  @PostMapping("/teachers/{teacherId}/classes")
  public Long createClass(@PathVariable Long teacherId, @RequestParam String className)
      throws UsersNotFoundException {
    return classesService.createClass(teacherId, className);
  }

  /**
   * * For Teacher, Generate a QR code for students to attend QR code is basically created by provide
   *   a link to attendances URL
   *
   * @param classId
   * @param request
   * @return
   * @throws Exception
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
   *  * For Teacher, Generate XML/JSON/CSV list of students that attended given class
   *
   * @param classId
   * @return
   * @throws ClassesNotFoundException
   */
  @GetMapping("/{classId}/attendances")
  public List<String> generateClassAttendance(@PathVariable Long classId)
      throws ClassesNotFoundException {
    return attendancesService.getClassAttendances(classId);
  }

  /**
   *  * For Student, Create an attendance record into database
   *
   * @param classId
   * @param telephoneNumber
   */
  @ResponseStatus(code = HttpStatus.OK)
  @PostMapping("/{classId}/attendances")
  public void createAttendance(@PathVariable Long classId, @RequestParam String telephoneNumber) {
    System.out.println(telephoneNumber + " has attended " + classId);
    attendancesService.createAttendance(classId, telephoneNumber);
  }

}
