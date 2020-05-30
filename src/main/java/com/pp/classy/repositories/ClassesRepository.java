package com.pp.classy.repositories;

import com.pp.classy.entities.Classes;
import com.pp.classy.entities.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassesRepository extends JpaRepository<Classes, Long> {

  List<Classes> findAllByTeacher(User teacher);

}
