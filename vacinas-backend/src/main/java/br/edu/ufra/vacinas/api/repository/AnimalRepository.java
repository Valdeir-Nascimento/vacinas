package br.edu.ufra.vacinas.api.repository;

import br.edu.ufra.vacinas.api.model.Animal;
import br.edu.ufra.vacinas.api.model.Raca;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends GenericRepository<Animal> {

	@Query("SELECT a FROM Animal a WHERE a.raca = : raca")
	List<Animal> obterAnimaisPorRaca(@Param("raca") Raca raca);

}
