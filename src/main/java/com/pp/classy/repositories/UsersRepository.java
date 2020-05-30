package com.pp.classy.repositories;

import com.pp.classy.entities.Classes;
import com.pp.classy.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

}
