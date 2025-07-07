package com.senzu.UserService.repository;


import com.senzu.UserService.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUserName(String userName);

    @Query(value = "SELECT * from USER_DETAILS",nativeQuery = true)
    Optional<List<User>> getAllUsers();
}
