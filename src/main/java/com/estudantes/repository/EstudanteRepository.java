package com.estudantes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estudantes.model.Estudante;

public interface EstudanteRepository extends JpaRepository<Estudante, Long>{

}
