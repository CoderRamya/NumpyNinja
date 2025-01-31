package com.nn.assignment3.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "tbl_lms_program")
@Data
public class Program {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer program_id;

	@Column(name = "program_name")
	@JsonProperty("program_name")
	@NotNull(message = "Program Name cannot be null")
	private String programName;

	private String program_description;

	@NotNull
	@NotNull(message = "Program Status cannot be null")
	private String program_status;

}
