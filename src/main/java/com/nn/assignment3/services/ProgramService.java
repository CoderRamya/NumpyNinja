package com.nn.assignment3.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nn.assignment3.entity.Batch;
import com.nn.assignment3.entity.Program;
import com.nn.assignment3.exceptions.ResourceNotFoundException;
import com.nn.assignment3.jpa.ProgramRepository;

@Service
public class ProgramService {
	
	
	private final ProgramRepository repository;

	@Autowired
	public ProgramService(ProgramRepository ProgramRepo) {
		this.repository = ProgramRepo;
	}
	
	public Program createEntity(Program entity) {
		//validations
        return repository.save(entity); // Saves entity to the database
    }

    public List<Program> getAllEntities() {
        return repository.findAll(); // Fetch all records
    }

    public Optional<Program> getEntityById(Integer id) {
        return repository.findById(id); // Fetch by ID
    }

    public Program updateEntity(Integer id, Program entity) {
    	Program existingProgram = repository.findById(entity.getProgram_id())
                .orElseThrow(() -> new ResourceNotFoundException("Program with ID " + entity.getProgram_id() + " not found"));
   
        if (!existingProgram.getProgram_name().equals(entity.getProgram_name())) {
            Optional<Program> batchWithSameName = repository.findByProgramName(entity.getProgram_name());
            if (batchWithSameName.isPresent()) {
                throw new ResourceNotFoundException("Program Batch '" + entity.getProgram_name() + "' already exists.");
            }
        }
        existingProgram.setProgram_description(entity.getProgram_description());
        existingProgram.setProgram_name(entity.getProgram_name());
        existingProgram.setProgram_status(entity.getProgram_status());
    	return repository.save(existingProgram); // Updates if ID exists
    }

    public void deleteEntity(Program prog) {
        ///add validations
    	
    	repository.delete(prog); // Deletes entity
    }


	
	
	

}
