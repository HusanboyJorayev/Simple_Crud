package com.example.simple_crud.repository;

import com.example.simple_crud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User>findByIdAndDeletedAtIsNull(Integer id);
}
