package com.nn.assignment3.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nn.assignment3.entity.Batch;
import com.nn.assignment3.entity.Program;
import com.nn.assignment3.services.ProgramService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/programs")
public class ProgramController {

	@Autowired
	private ProgramService programServ;

	@GetMapping
	public List<Program> findAllProgrames() {
		return programServ.getAllEntities();
	}

	@GetMapping("/{id}")
	public Optional<Program> getProgramById(@PathVariable Integer id) {
		return programServ.getEntityById(id);

	}

	@PostMapping
	public ResponseEntity<?> createProgram(@Valid @RequestBody Program Program) {
		return programServ.createEntity(Program);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateProgram(@PathVariable Integer id, @Valid @RequestBody Program program) {
		return programServ.updateEntity(id, program);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> DeleteProgram(@PathVariable Integer id) {
		Program Program = programServ.getEntityById(id).orElseThrow(() -> new EntityNotFoundException("Program Not Found!"));
		programServ.deleteEntity(Program);
		return ResponseEntity.ok().build();
	}

}
