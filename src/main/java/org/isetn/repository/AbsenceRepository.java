package org.isetn.repository;

import java.util.List;
import java.util.Optional;

import org.isetn.entities.Absence;
import org.isetn.entities.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbsenceRepository extends JpaRepository<Absence, Long> {
    // You can add custom query methods here if needed
    List<Absence> findByEtudiantId(Long codClass);
}
