package com.nn.assignment3.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nn.assignment3.entity.Program;

@Repository
public interface ProgramRepository extends JpaRepository<Program, Integer>{

	Optional<Program> findByProgramName(String program_name);
}
