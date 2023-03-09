package com.example.FGPC_HMS.Repositary;

import com.example.FGPC_HMS.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Patient_repositary extends JpaRepository<Patient, Integer> {
}
