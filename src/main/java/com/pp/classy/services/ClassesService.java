package com.pp.classy.services;

import com.pp.classy.entities.Classes;
import com.pp.classy.entities.User;
import com.pp.classy.exception.ClassesNotFoundException;
import com.pp.classy.exception.UsersNotFoundException;
import com.pp.classy.repositories.ClassesRepository;
import com.pp.classy.repositories.UsersRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ClassesService {

  final ClassesRepository classesRepository;
  final UsersRepository usersRepository;

  @Value("${classy.frontend.url.checkin}")
  private String frontendCheckinUrl;

  public ClassesService(
      ClassesRepository classesRepository,
      UsersRepository usersRepository) {
    this.classesRepository = classesRepository;
    this.usersRepository = usersRepository;
  }

  public Long createClass(Long teacherId, String className) throws UsersNotFoundException {
    Optional<User> userOptional = usersRepository.findById(teacherId);

    if (userOptional.isEmpty()) {
      throw new UsersNotFoundException();
    }

    User teacher = userOptional.get();

    Classes creatingClass = new Classes();
    creatingClass.setName(className);
    creatingClass.setTeacher(teacher);
    Classes savedClass = classesRepository.save(creatingClass);
    return savedClass.getId();
  }

  public List<Classes> getAllClasses(Long teacherId) throws UsersNotFoundException {

    Optional<User> userOptional = usersRepository.findById(teacherId);

    if (userOptional.isEmpty()) {
      throw new UsersNotFoundException();
    }

    User teacher = userOptional.get();

    return classesRepository.findAllByTeacher(teacher);
  }

  public String generateCheckInUrl(Long classId) throws ClassesNotFoundException {

    Optional<Classes> classOptional = classesRepository.findById(classId);
    if (classOptional.isEmpty()) {
      throw new ClassesNotFoundException();
    }

    Classes foundClass = classOptional.get();

    return frontendCheckinUrl + "?className=" + foundClass.getName() + "&classId=" + foundClass
        .getId();
  }
}
