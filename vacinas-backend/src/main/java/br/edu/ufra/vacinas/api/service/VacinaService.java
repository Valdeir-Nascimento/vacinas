package br.edu.ufra.vacinas.api.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufra.vacinas.api.exception.VacinaNaoEncontradaException;
import br.edu.ufra.vacinas.api.model.Animal;
import br.edu.ufra.vacinas.api.model.Vacina;
import br.edu.ufra.vacinas.api.repository.VacinaRepository;

@Service
public class VacinaService {

    @Autowired
    private VacinaRepository vacinaRepository;

    public List<Vacina> obterVacinasPorAnimal(Animal animal) {
        return vacinaRepository.obterVacinasPorAnimal(animal);
    }

    public Vacina findById(Integer idVacina) {
        var vacina = vacinaRepository.findById(idVacina);
        return vacina.orElseThrow(() -> new VacinaNaoEncontradaException("Não existe cadastro de Vacina com Id: " + idVacina));
    }

    public Vacina salvar(Vacina vacina) {
        return vacinaRepository.save(vacina);
    }

    public void excluir(Integer idVacina) {
        vacinaRepository.deleteById(idVacina);
    }



}
