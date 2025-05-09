package com.security.spring.security1.repository;


import com.security.spring.security1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User,Integer> {
    User findByUserName(String userName);
}
