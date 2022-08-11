package com.example.bookstore.repository;

import com.example.bookstore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long>, CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);



}
