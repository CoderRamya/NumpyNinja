package com.nn.assignment3.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.nn.assignment3.entity.Program;
import com.nn.assignment3.exceptions.ProgramLinkedToBatchException;
import com.nn.assignment3.exceptions.ResourceAlreadyExistsException;
import com.nn.assignment3.exceptions.ResourceNotFoundException;
import com.nn.assignment3.jpa.BatchRepository;
import com.nn.assignment3.jpa.ProgramRepository;

@Service
public class ProgramService {

	@Autowired
	private BatchRepository batchRepository;

	@Autowired
	private ProgramRepository repository;

	public ResponseEntity<?> createEntity(Program entity) {

		Optional<Program> sameProgramName = repository.findByProgramName(entity.getProgramName());
		if (sameProgramName.isPresent()) {
			throw new ResourceAlreadyExistsException("Program '" + entity.getProgramName() + "' already exists.");
		}

		repository.save(entity); // Saves entity to the database
		return new ResponseEntity<>("Saved Successfully", HttpStatus.OK);
	}

	public List<Program> getAllEntities() {
		return repository.findAll(); // Fetch all records
	}

	public Optional<Program> getEntityById(Integer id) {
		return repository.findById(id); // Fetch by ID
	}

	public ResponseEntity<?> updateEntity(Integer id, Program entity) {
		Program existingProgram = repository.findById(id).orElseThrow(
				() ->  new ResourceNotFoundException("Program with ID " + entity.getProgram_id() + " not found"));

		if (!existingProgram.getProgramName().equals(entity.getProgramName())) {
			Optional<Program> sameProgramName = repository.findByProgramName(entity.getProgramName());
			if (sameProgramName.isPresent()) {
				throw new ResourceAlreadyExistsException(
						"Program Batch '" + entity.getProgramName() + "' already exists.");
			}
		}
		existingProgram.setProgram_description(entity.getProgram_description());
		existingProgram.setProgramName(entity.getProgramName());
		existingProgram.setProgram_status(entity.getProgram_status());
		repository.save(existingProgram); // Updates if ID exists
		return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
	}

	public void deleteEntity(Program prog) {
		/// add validations
		if (!batchRepository.existsByProgramId(prog.getProgram_id()))
			repository.delete(prog); // Deletes entity
		else
			throw new ProgramLinkedToBatchException(
					"Program '" + prog.getProgramName() + "' is linked to batches and cannot be deleted!");
	}

}
