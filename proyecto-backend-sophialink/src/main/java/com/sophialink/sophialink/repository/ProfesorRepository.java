package com.sophialink.sophialink.repository;

import com.sophialink.sophialink.entity.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Integer> {
    Optional<Profesor> findByCorreoInstitucional(String correoInstitucional);
}
