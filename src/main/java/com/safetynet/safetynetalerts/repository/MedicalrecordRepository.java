package com.safetynet.safetynetalerts.repository;

import com.safetynet.safetynetalerts.model.Medicalrecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalrecordRepository extends CrudRepository<Medicalrecord, Long> {
}
