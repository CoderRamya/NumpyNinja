package com.nn.assignment3.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nn.assignment3.entity.Batch;
import com.nn.assignment3.exceptions.ResourceNotFoundException;
import com.nn.assignment3.services.BatchService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/batches")
@Validated
public class BatchController {

	@Autowired
	private BatchService batchServ;

	@GetMapping
	public List<Batch> findAllBatches() {
		return batchServ.getAllEntities();
	}

	@GetMapping("/{id}")
	public Optional<Batch> getBatchById(@PathVariable Integer id) {
		return batchServ.getEntityById(id);

	}

	@PostMapping
	public ResponseEntity<?> createBatch(@Valid @RequestBody Batch batch) {
		return batchServ.createEntity(batch);
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> updateBatch(@PathVariable Integer id, @Valid @RequestBody Batch batch) {
		return batchServ.updateEntity( id,batch);

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> DeleteBatch(@PathVariable Integer id) {
		Batch batch = batchServ.getEntityById(id).orElseThrow(() -> new ResourceNotFoundException("Batch Not Found!"));
		batchServ.deleteEntity(batch);
		return ResponseEntity.ok().build();
	}

}
