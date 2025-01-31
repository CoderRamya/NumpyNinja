package com.nn.assignment3.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nn.assignment3.entity.Batch;

@Repository
public interface BatchRepository extends JpaRepository<Batch, Integer> {

	Optional<Batch> findByBatchName(String batch_name);
	boolean existsByProgramId(Integer batch_program_id);
	
}
