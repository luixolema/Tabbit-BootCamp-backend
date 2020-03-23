package com.tabit.dcm2.repository;

import com.tabit.dcm2.entity.Guest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGuestRepo extends JpaRepository<Guest, Long> {
    List<Guest> findByCheckedin(boolean checkein);
}
