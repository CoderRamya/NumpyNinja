package com.nn.assignment3.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Entity
@Table(name = "tbl_lms_batch")
@Data
public class Batch {
	@Id
	@NotNull
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int batch_id;
	
	@Column(name="batch_name") 
	@JsonProperty("batch_name") 
	@NotNull(message = "Batch Name cannot be Null")
    private String batchName ;
    
    private String batch_description ;
    
    @NotNull(message = "Status cannot be Null")
    private String batch_status ;

    @NotNull(message = "Program Id cannot be Null")
    @Column(name = "program_id")
    @JsonProperty("batch_program_id") 
    private Integer programId;
    
    @NotNull(message = "Batch # of classes cannot be Null")
    private int batch_no_of_classes=1 ;
    
    @ManyToOne
    @JoinColumn(name = "batch_program_id", referencedColumnName = "program_id", nullable = false)
    private Program program;  // The Program object is still used for JPA mapping purposes

	

}
