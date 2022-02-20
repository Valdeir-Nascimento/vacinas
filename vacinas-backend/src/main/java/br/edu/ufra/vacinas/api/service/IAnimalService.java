package br.edu.ufra.vacinas.api.service;

import br.edu.ufra.vacinas.api.model.Animal;
import br.edu.ufra.vacinas.api.model.Raca;

import java.util.List;

public interface IAnimalService {

    List<Animal> obter(Raca raca);

}
