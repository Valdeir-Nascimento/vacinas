package br.edu.ufra.vacinas.api.service.impl;


import br.edu.ufra.vacinas.api.exception.VacinaNaoEncontradaException;
import br.edu.ufra.vacinas.api.model.Animal;
import br.edu.ufra.vacinas.api.model.Vacina;
import br.edu.ufra.vacinas.api.repository.VacinaRepository;
import br.edu.ufra.vacinas.api.service.IVacinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacinaServiceImpl extends GenericServiceImpl<Vacina> implements IVacinaService {

    @Autowired
    private VacinaRepository vacinaRepository;
    @Autowired
    private AnimalServiceImpl animalService;

    @Override
    public List<Vacina> obter(Animal animal) {
        return vacinaRepository.obterVacinasPorAnimal(animal);
    }

    @Override
    public Vacina buscar(Integer idAnimal, Integer idVacina) {
        if (animalService.findById(idAnimal) != null) {
            return vacinaRepository
                    .findById(idVacina)
                    .orElseThrow(() -> new VacinaNaoEncontradaException(idVacina));
        }
        return null;
    }

}
