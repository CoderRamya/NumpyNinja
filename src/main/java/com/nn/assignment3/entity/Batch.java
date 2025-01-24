package com.nn.assignment3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "tbl_lms_batch")
public class Batch {
	@Id
	@NotNull
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int batch_id;
	
	@Column(name="batch_name",nullable = false, unique = true) 
    private String batchName ;
    
    private String batch_description ;
    
    private String batch_status ;

    @Column(name = "program_id", insertable = false, updatable = false)
    private Integer batch_program_id;
    
    @NotNull
    private int batch_no_of_classes=1 ;
    
    @ManyToOne
    @JoinColumn(name = "batch_program_id", referencedColumnName = "program_id", nullable = false)
    private Program program;  // The Program object is still used for JPA mapping purposes

	public int getBatch_id() {
		return batch_id;
	}

	public void setBatch_id(int batch_id) {
		this.batch_id = batch_id;
	}

	public String getBatch_name() {
		return batchName;
	}

	public void setBatch_name(String batch_name) {
		this.batchName = batch_name;
	}

	public String getBatch_description() {
		return batch_description;
	}

	public void setBatch_description(String batch_description) {
		this.batch_description = batch_description;
	}

	public String getBatch_status() {
		return batch_status;
	}

	public void setBatch_status(String batch_status) {
		this.batch_status = batch_status;
	}

	public Integer getBatch_program_id() {
		return batch_program_id;
	}

	public void setBatch_program_id(Integer batch_program_id) {
		this.batch_program_id = batch_program_id;
	}

	public int getBatch_no_of_classes() {
		return batch_no_of_classes;
	}

	public void setBatch_no_of_classes(int batch_no_of_classes) {
		this.batch_no_of_classes = batch_no_of_classes;
	}
	
	

	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	public Batch() {}
	public Batch(@NotNull int batch_id, @NotNull String batch_name, String batch_description, String batch_status,
			@NotNull int batch_program_id, @NotNull int batch_no_of_classes) {
		super();
		this.batch_id = batch_id;
		this.batchName = batch_name;
		this.batch_description = batch_description;
		this.batch_status = batch_status;
		this.batch_program_id = batch_program_id;
		this.batch_no_of_classes = batch_no_of_classes;
	}
	

	@Override
	public String toString() {
		return "Batch [batch_id=" + batch_id + ", batch_name=" + batchName + ", batch_description=" + batch_description
				+ ", batch_status=" + batch_status + ", batch_program_id=" + batch_program_id + ", batch_no_of_classes="
				+ batch_no_of_classes + "]";
	}


}
