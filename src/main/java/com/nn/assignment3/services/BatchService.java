package com.nn.assignment3.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nn.assignment3.entity.Batch;
import com.nn.assignment3.entity.Program;
import com.nn.assignment3.exceptions.ResourceAlreadyExistsException;
import com.nn.assignment3.exceptions.ResourceNotFoundException;
import com.nn.assignment3.jpa.BatchRepository;
import com.nn.assignment3.jpa.ProgramRepository;

@Service
public class BatchService {

	@Autowired
	private BatchRepository repository;

	@Autowired
	private ProgramRepository progRepository;

	public ResponseEntity<?> createEntity(Batch entity) {
		Program program = progRepository.findById(entity.getProgramId()).orElseThrow(
				() -> new ResourceNotFoundException("Program with ID " + entity.getProgramId() + " not found"));
		entity.setProgram(program);
		entity.setProgramId(program.getProgram_id());
		Optional<Batch> batchWithSameName = repository.findByBatchName(entity.getBatchName());
		if (batchWithSameName.isPresent()) {
			throw new ResourceAlreadyExistsException("Batch name '" + entity.getBatchName() + "' already exists.");
		}
		repository.save(entity); // Saves entity to the database
		return new ResponseEntity<>("Saved Successfully", HttpStatus.OK);
	}

	public List<Batch> getAllEntities() {
		return repository.findAll(); // Fetch all records
	} 

	public ResponseEntity<?> updateEntity(Integer id, Batch entity) {
		// Ensure the Program exists
		Program program = progRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Program with ID " + entity.getProgramId() + " not found"));

		// Check if the Batch exists
		Batch existingBatch = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Batch with ID " + id + " not found"));

		// Check if batchName has been changed and if it exists already
		if (!existingBatch.getBatchName().equals(entity.getBatchName())) {
			Optional<Batch> batchWithSameName = repository.findByBatchName(entity.getBatchName());
			if (batchWithSameName.isPresent()) {
				throw new ResourceNotFoundException("Batch name '" + entity.getBatchName() + "' already exists.");
			}
		}

//        // Update the batch details
		existingBatch.setBatchName(entity.getBatchName());
		existingBatch.setProgram(program);
		existingBatch.setProgramId(program.getProgram_id());
		existingBatch.setBatch_description(entity.getBatch_description());
		existingBatch.setBatch_no_of_classes(entity.getBatch_no_of_classes());
		existingBatch.setBatch_status(entity.getBatch_status());
		repository.save(existingBatch);
		return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
	}

	public void deleteEntity(Batch batch) {
		repository.delete(batch); // Deletes entity
	}

	public Optional<Batch> getEntityById(Integer id) {
		return repository.findById(id);
	}

}
