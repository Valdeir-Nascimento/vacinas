package br.edu.ufra.vacinas.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.ufra.vacinas.api.exception.AnimalNaoEncontradoException;
import br.edu.ufra.vacinas.api.model.Animal;
import br.edu.ufra.vacinas.api.model.Raca;
import br.edu.ufra.vacinas.api.repository.AnimalRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnimalService {

    @Autowired
    private AnimalRepository animalRepository;

    public List<Animal> obter(Raca raca) {
        return animalRepository.obterAnimaisPorRaca(raca);
    }

    public List<Animal> listar() {
        return animalRepository.findAll();
    }

    public Animal buscar(Integer idAnimal) {
        return animalRepository
                .findById(idAnimal)
                .orElseThrow(() -> new AnimalNaoEncontradoException(idAnimal));
    }

    @Transactional
    public Animal salvar(Animal animal) {
        return animalRepository.save(animal);
    }

    public void excluir(Integer idAnimal) {
        try {
            animalRepository.deleteById(idAnimal);
            animalRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new AnimalNaoEncontradoException(idAnimal);
        }
    }

}
