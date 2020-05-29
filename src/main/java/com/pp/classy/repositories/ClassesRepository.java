package com.pp.classy.repositories;

import com.pp.classy.entities.Classes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassesRepository  extends JpaRepository<Classes, Long> {
}
