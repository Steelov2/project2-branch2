package com.example.bookstore.Repos;

import com.example.bookstore.entities.Role;
import com.example.bookstore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long>, CrudRepository<User, Long> {

    Optional<User> findByUsername(String username);



}
