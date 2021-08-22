package com.librarysys.backend.repository;

import com.librarysys.backend.domain.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

/** Interface to be auto implimented by {@link JpaRepository} implementations
 * 
 * @author BugBusters
 * 
  */
public interface UserRepository extends JpaRepository<Long, User>{
     
}
