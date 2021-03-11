package com.safetynet.safetynetalerts.repository;

import com.safetynet.safetynetalerts.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM persons p WHERE p.last_name=?1 OR p.first_name=?2", nativeQuery = true)
    void deleteByLastNameAndFirstName(String lastName, String firstName);

}
