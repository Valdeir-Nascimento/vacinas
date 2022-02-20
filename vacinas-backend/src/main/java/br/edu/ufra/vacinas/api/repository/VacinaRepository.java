package br.edu.ufra.vacinas.api.repository;

import br.edu.ufra.vacinas.api.model.Animal;
import br.edu.ufra.vacinas.api.model.Vacina;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacinaRepository extends GenericRepository<Vacina> {

	@Query("SELECT v FROM Vacina v WHERE v.animal = :animal")
	List<Vacina> obterVacinasPorAnimal(Animal animal);

}
