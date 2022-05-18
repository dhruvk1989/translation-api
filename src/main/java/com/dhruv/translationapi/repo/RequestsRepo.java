package com.dhruv.translationapi.repo;

import com.dhruv.translationapi.entities.Requests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestsRepo extends JpaRepository<Requests, Long> {
}
