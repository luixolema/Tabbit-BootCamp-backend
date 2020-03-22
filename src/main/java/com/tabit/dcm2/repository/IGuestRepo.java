package com.tabit.dcm2.repository;

import com.tabit.dcm2.entity.Guest;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IGuestRepo extends PagingAndSortingRepository<Guest, Long> {
    List<Guest> findByCheckedin(boolean checkein);
}
