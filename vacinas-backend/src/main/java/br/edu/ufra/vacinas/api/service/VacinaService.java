package br.edu.ufra.vacinas.api.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.ufra.vacinas.api.exception.VacinaNaoEncontradaException;
import br.edu.ufra.vacinas.api.model.Animal;
import br.edu.ufra.vacinas.api.model.Vacina;
import br.edu.ufra.vacinas.api.repository.VacinaRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VacinaService {

    @Autowired
    private VacinaRepository vacinaRepository;
    @Autowired
    private AnimalService animalService;

    public List<Vacina> obter(Animal animal) {
        return vacinaRepository.obterVacinasPorAnimal(animal);
    }

    public Vacina buscar(Integer idAnimal, Integer idVacina) {
        if (animalService.buscar(idAnimal) != null) {
            return vacinaRepository
                    .findById(idVacina)
                    .orElseThrow(() -> new VacinaNaoEncontradaException(idVacina));
        }
        return null;
    }

    @Transactional
    public Vacina salvar(Vacina vacina) {
        return vacinaRepository.save(vacina);
    }

    public void excluir(Integer idVacina) {
        try {
            vacinaRepository.deleteById(idVacina);
            vacinaRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new VacinaNaoEncontradaException(idVacina);
        }
    }


}
