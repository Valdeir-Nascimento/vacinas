package br.edu.ufra.vacinas.api.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufra.vacinas.api.exception.AnimalNaoEncontradoException;
import br.edu.ufra.vacinas.api.model.Animal;
import br.edu.ufra.vacinas.api.model.Raca;
import br.edu.ufra.vacinas.api.repository.AnimalRepository;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public List<Animal> obterAnimaisPorRaca(Raca raca) {
        return animalRepository.obterAnimaisPorRaca(raca);
    }

    public List<Animal> obterAnimais() {
        return animalRepository.findAll();
    }

    public Animal findById(Integer idAnimal) {
        var animal = animalRepository.findById(idAnimal);
        return animal.orElseThrow(() -> new AnimalNaoEncontradoException("Não existe cadastro de Animal com Id: " + idAnimal));
    }

    public Animal salvar(Animal animal) {
        return animalRepository.save(animal);
    }

    public void excluir(Integer idAnimal) {
        animalRepository.deleteById(idAnimal);
    }

}
