package com.example.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Entity.ClinicInventory;

@Repository
public interface ClinicInventoryRepository extends JpaRepository<ClinicInventory, Long>{

}
