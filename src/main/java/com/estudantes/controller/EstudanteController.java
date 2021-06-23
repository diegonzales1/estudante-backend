package com.estudantes.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.estudantes.model.Estudante;
import com.estudantes.service.EstudanteService;

@RestController
@RequestMapping("/estudantes")
@CrossOrigin
public class EstudanteController {

	@Autowired
	private EstudanteService service;

	// Lista todos os estudantes
	@GetMapping
	public List<Estudante> listarEstudantes() {
		return service.listar();
	}

	// Busca um estudante pelo código
	@GetMapping("/{codigo}")
	public Optional<Estudante> buscar(@PathVariable Long codigo) {
		return service.buscar(codigo);
	}

	// Salva um novo estudante
	@PostMapping()
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Estudante> novo(@Validated @RequestBody Estudante estudante, HttpServletResponse response) {
		return service.cadastraNovoEstudante(estudante, response);
	}

	// Atualiza um estudante
	@PutMapping("/{codigo}")
	public ResponseEntity<Estudante> atualiza(@PathVariable Long codigo, @Validated @RequestBody Estudante estudante) {
		Estudante estudanteSalvo = service.atualizaEstudante(codigo, estudante);
		return ResponseEntity.ok(estudanteSalvo);
	}
	
	// Deleta um estudante
	@DeleteMapping("/{codigo}")
	public ResponseEntity<Void> deleta(@PathVariable Long codigo){
		return service.deletaUmEstudante(codigo);
	}

}
