package com.example.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.Entity.Medication;

@Repository
public interface MedicationsReposiory extends JpaRepository<Medication, Integer> 
{
	@Query(value="select m.medicationName from Medication m ")
	List<String> getAllMedicationNames();
	@Query(value="select m from Medication m where m.medicationName=:medicationName")
	Medication findByMedicationNmae(String medicationName);
	
}
