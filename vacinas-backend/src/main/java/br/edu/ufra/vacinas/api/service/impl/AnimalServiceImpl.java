package br.edu.ufra.vacinas.api.service.impl;

import java.util.List;

import br.edu.ufra.vacinas.api.service.IAnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufra.vacinas.api.model.Animal;
import br.edu.ufra.vacinas.api.model.Raca;
import br.edu.ufra.vacinas.api.repository.AnimalRepository;

@Service
public class AnimalServiceImpl extends GenericServiceImpl<Animal> implements IAnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    @Override
    public List<Animal> obter(Raca raca) {
        return animalRepository.obterAnimaisPorRaca(raca);
    }

}
