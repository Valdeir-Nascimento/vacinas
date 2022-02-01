package br.edu.ufra.vacinas.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.ufra.vacinas.api.model.Animal;
import br.edu.ufra.vacinas.api.model.Vacina;

public interface VacinaRepository extends JpaRepository<Vacina, Integer> {

	@Query("SELECT v FROM Vacina v WHERE v.animal = :animal")
	List<Vacina> obterVacinasPorAnimal(Animal animal);

}
