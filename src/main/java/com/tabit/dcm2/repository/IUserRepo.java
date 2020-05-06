package com.tabit.dcm2.repository;

import com.tabit.dcm2.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepo extends JpaRepository<User, Long> {

    @Query("FROM users u Where u.email = ?1 or u.userName = ?1")
    Optional<User> findByUserName(String userName);
}
