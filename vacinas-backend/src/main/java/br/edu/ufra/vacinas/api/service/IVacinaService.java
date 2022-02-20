package br.edu.ufra.vacinas.api.service;

import br.edu.ufra.vacinas.api.model.Animal;
import br.edu.ufra.vacinas.api.model.Vacina;

import java.util.List;

public interface IVacinaService {

    List<Vacina> obter(Animal animal);
    Vacina buscar(Integer idAnimal, Integer idVacina);

}
