package org.isetn.RestControllers;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.isetn.entities.Absence;
import org.isetn.entities.Classe;
import org.isetn.entities.Etudiant;
import org.isetn.entities.Matiere;
import org.isetn.repository.ClasseRepository;
import org.isetn.repository.MatiereRepository;
import org.isetn.repository.AbsenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("absence")

public class AbsenceController {
	@Autowired
	private AbsenceRepository AbsenceRepository;

	@GetMapping("/getByMatiereIdAndDate")
	public List<Absence> findByMatiereIdAndDate(
			@RequestParam Long matiereId,
			@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		LocalDateTime startDateTime = LocalDateTime.of(date, LocalTime.MIN);
		LocalDateTime endDateTime = LocalDateTime.of(date, LocalTime.MAX);

		return AbsenceRepository.findByMatiereMatiereIdAndDateBetween(matiereId, startDateTime, endDateTime);
	}

	@GetMapping("/getByEtudiantId/{etudiantId}")
	public List<Absence> findByClasseIdClas(@PathVariable("etudiantId") Long etudiantId) {
		return AbsenceRepository.findByEtudiantId(etudiantId);
	}

	@PostMapping("/add")
	public Absence add(@RequestBody Absence absence) {
		System.out.println(absence.toString());
		return AbsenceRepository.save(absence);
	}

	@GetMapping("/all")
	public List<Absence> getAll() {
		return AbsenceRepository.findAll();
	}

	@DeleteMapping("/delete")
	public void delete(@Param("id") Long id) {
		Absence abs = AbsenceRepository.findById(id).get();
		AbsenceRepository.delete(abs);
	}

	@PutMapping("/update")
	public Absence update(@RequestBody Absence absence) {
		return AbsenceRepository.save(absence);
	}
}
