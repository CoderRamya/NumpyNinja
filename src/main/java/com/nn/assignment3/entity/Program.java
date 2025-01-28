package com.nn.assignment3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_lms_program")
public class Program {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer program_id; 
	
	@Column(name="program_name", nullable=false, unique=true)
    private String programName;
		
    private String program_description;
	
    @NotNull
    private String    program_status;
    

    public Program() {}
    
	public Program(int program_id, @NotNull String program_name, String program_description,
			@NotNull String program_status) {
		super();
		this.program_id = program_id;
		this.programName = program_name;
		this.program_description = program_description;
		this.program_status = program_status;
	}

	public Integer getProgram_id() {
		return program_id;
	}

	public void setProgram_id(Integer program_id) {
		this.program_id = program_id;
	}

	public String getProgram_name() {
		return programName;
	}

	public void setProgram_name(String program_name) {
		this.programName = program_name;
	}

	public String getProgram_description() {
		return program_description;
	}

	public void setProgram_description(String program_description) {
		this.program_description = program_description;
	}

	public String getProgram_status() {
		return program_status;
	}

	public void setProgram_status(String program_status) {
		this.program_status = program_status;
	} 
    
}
