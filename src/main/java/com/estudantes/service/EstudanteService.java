package com.estudantes.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.estudantes.model.Estudante;
import com.estudantes.repository.EstudanteRepository;

@Service
public class EstudanteService {

	@Autowired
	private EstudanteRepository repository;

	// Mostra todos os estudantes
	public List<Estudante> listar() {
		return repository.findAll();
	}

	// Procura um estudante pelo código
	public Optional<Estudante> buscar(Long codigo) {
		Optional<Estudante> estudante = repository.findById(codigo);

		if (estudante.isEmpty())
			throw new EmptyResultDataAccessException(1);

		return estudante;
	}

	// Adiciona um novo estudante
	public ResponseEntity<Estudante> cadastraNovoEstudante(Estudante estudante, HttpServletResponse response) {
		Estudante estudanteSalvo = repository.save(estudante);
		return ResponseEntity.status(HttpStatus.CREATED).body(estudanteSalvo);
	}

	// Deleta um estudante
	public ResponseEntity<Void> deletaUmEstudante(Long codigo) {
		if (!repository.existsById(codigo))
			return ResponseEntity.notFound().build();

		repository.deleteById(codigo);
		return ResponseEntity.noContent().build();
	}
	
	// Atualiza um estudante
	public Estudante atualizaEstudante(Long codigo, Estudante estudante) {
		Optional<Estudante> estudanteSalvo = buscar(codigo);
		BeanUtils.copyProperties(estudante, estudanteSalvo.get(), "id");
		return repository.save(estudanteSalvo.get());
	}
}
