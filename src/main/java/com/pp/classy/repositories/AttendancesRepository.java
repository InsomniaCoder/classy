package com.pp.classy.repositories;

import com.pp.classy.entities.Attendances;
import com.pp.classy.entities.Classes;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendancesRepository extends JpaRepository<Attendances, Long> {
  public List<Attendances> findAttendancesByClasses(Classes classes);
}
