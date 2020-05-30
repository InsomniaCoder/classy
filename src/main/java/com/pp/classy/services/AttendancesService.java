package com.pp.classy.services;

import com.pp.classy.entities.Attendances;
import com.pp.classy.entities.Classes;
import com.pp.classy.exception.ClassesNotFoundException;
import com.pp.classy.repositories.AttendancesRepository;
import com.pp.classy.repositories.ClassesRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class AttendancesService {

  final AttendancesRepository attendancesRepository;
  final ClassesRepository classesRepository;

  public AttendancesService(
      AttendancesRepository attendancesRepository,
      ClassesRepository classesRepository) {
    this.attendancesRepository = attendancesRepository;
    this.classesRepository = classesRepository;
  }

  public void createAttendance(Long classId, String telephoneNumber) {
    Optional<Classes> classesOptional = classesRepository.findById(classId);
    classesOptional.ifPresent(classes -> {
      Attendances attendances = new Attendances();
      attendances.setClasses(classes);
      attendances.setTelephoneNumber(telephoneNumber);
      attendancesRepository.save(attendances);
    });
  }

  public List<String> getClassAttendances(Long classId) throws ClassesNotFoundException {
    Optional<Classes> classesOptional = classesRepository.findById(classId);

    if (classesOptional.isEmpty()) {
      throw new ClassesNotFoundException();
    }

    Classes classes = classesOptional.get();
    return attendancesRepository.findAttendancesByClasses(classes)
        .stream()
        .map(Attendances::getTelephoneNumber)
        .collect(Collectors.toList());
  }

}
