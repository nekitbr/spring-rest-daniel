package com.web.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.api.model.Farmacia;

@Repository
public interface FarmaciaRepository extends JpaRepository<Farmacia, Long>{}
