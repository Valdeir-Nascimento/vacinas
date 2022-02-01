package br.edu.ufra.vacinas.api.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ufra.vacinas.api.exception.RacaNaoEncontradaException;
import br.edu.ufra.vacinas.api.model.Raca;
import br.edu.ufra.vacinas.api.repository.RacaRepository;

@Service
public class RacaService {

    @Autowired
    private RacaRepository racaRepository;

    public List<Raca> findAll() {
        return racaRepository.findAll();
    }

    public Raca findById(Integer idRaca) {
        var raca = racaRepository.findById(idRaca);
        return raca.orElseThrow(() -> new RacaNaoEncontradaException("Não existe cadastro de raça com Id: " + idRaca));
    }

    public Raca salvar(Raca raca) {
        return racaRepository.save(raca);
    }

    public void excluir(Integer idRaca) {
        racaRepository.deleteById(idRaca);
    }

}
