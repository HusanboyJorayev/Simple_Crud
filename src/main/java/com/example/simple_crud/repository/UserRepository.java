package com.example.simple_crud.repository;

import com.example.simple_crud.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByIdAndDeletedAtIsNull(Integer id);
    Page<User>findAllByDeletedAtIsNull(Pageable pageable);

    @Query(
            value = "select * from users  where id>=2",
            nativeQuery = true
    )
    List<User>getAllUsersByQuery();
}
