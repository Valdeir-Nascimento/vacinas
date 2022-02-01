package br.edu.ufra.vacinas.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.edu.ufra.vacinas.api.model.Animal;
import br.edu.ufra.vacinas.api.model.Raca;

import java.util.List;

public interface AnimalRepository extends JpaRepository<Animal, Integer> {

	@Query("SELECT a FROM Animal a WHERE a.raca = : raca")
	List<Animal> obterAnimaisPorRaca(@Param("raca") Raca raca);

}
