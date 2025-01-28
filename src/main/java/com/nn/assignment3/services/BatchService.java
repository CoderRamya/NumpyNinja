package com.nn.assignment3.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

	public Batch createEntity(Batch entity) {
		Program program = progRepository.findById(entity.getBatch_program_id()).orElseThrow(
				() -> new ResourceNotFoundException("Program with ID " + entity.getBatch_program_id() + " not found"));
		entity.setProgram(program);
		entity.setBatch_program_id(program.getProgram_id());
		Optional<Batch> batchWithSameName = repository.findByBatchName(entity.getBatch_name());
		if (batchWithSameName.isPresent()) {
			throw new ResourceAlreadyExistsException("Batch name '" + entity.getBatch_name() + "' already exists.");
		}
		return repository.save(entity); // Saves entity to the database
	}

	public List<Batch> getAllEntities() {
		return repository.findAll(); // Fetch all records
	}

	public Batch updateEntity(Integer id, Batch entity) {
		// Ensure the Program exists
		Program program = progRepository.findById(entity.getBatch_program_id()).orElseThrow(
				() -> new ResourceNotFoundException("Program with ID " + entity.getBatch_program_id() + " not found"));

		// Check if the Batch exists
		Batch existingBatch = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Batch with ID " + id + " not found"));

		// Check if batchName has been changed and if it exists already
		if (!existingBatch.getBatch_name().equals(entity.getBatch_name())) {
			Optional<Batch> batchWithSameName = repository.findByBatchName(entity.getBatch_name());
			if (batchWithSameName.isPresent()) {
				throw new ResourceNotFoundException("Batch name '" + entity.getBatch_name() + "' already exists.");
			}
		}

//        // Update the batch details
		existingBatch.setBatch_name(entity.getBatch_name());
		existingBatch.setProgram(program);
		existingBatch.setBatch_program_id(program.getProgram_id());
		existingBatch.setBatch_description(entity.getBatch_description());
		existingBatch.setBatch_no_of_classes(entity.getBatch_no_of_classes());
		existingBatch.setBatch_status(entity.getBatch_status());

		return repository.save(existingBatch);
	}

	public void deleteEntity(Batch batch) {
		repository.delete(batch); // Deletes entity
	}

	public Optional<Batch> getEntityById(Integer id) {
		return repository.findById(id);
	}

}
