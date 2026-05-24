package com.sophialink.sophialink.repository;

import com.sophialink.sophialink.entity.Universidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UniversidadRepository extends JpaRepository<Universidad, Integer> {
}
