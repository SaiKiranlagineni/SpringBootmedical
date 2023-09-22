package com.example.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "medication_table")
public class Medication 
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int medicationId;
    private String medicationName;
    
	public int getMedicationId() {
		return medicationId;
	}
	public void setMedicationId(int medicationId) {
		this.medicationId = medicationId;
	}
	public String getMedicationName() {
		return medicationName;
	}
	public void setMedicationName(String medicationName) {
		this.medicationName = medicationName;
	}
    
    
	
    
    
    
	
    
	
    
    
}
