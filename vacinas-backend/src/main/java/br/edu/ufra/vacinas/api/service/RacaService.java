package br.edu.ufra.vacinas.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.edu.ufra.vacinas.api.exception.RacaNaoEncontradaException;
import br.edu.ufra.vacinas.api.model.Raca;
import br.edu.ufra.vacinas.api.repository.RacaRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RacaService {

    @Autowired
    private RacaRepository racaRepository;

    public List<Raca> listar() {
        return racaRepository.findAll();
    }

    public Raca findById(Integer idRaca) {

        return racaRepository
                .findById(idRaca)
                .orElseThrow(() -> new RacaNaoEncontradaException(idRaca));
    }

    @Transactional
    public Raca salvar(Raca raca) {
        return racaRepository.save(raca);
    }

    public void excluir(Integer idRaca) {
        try {
            racaRepository.deleteById(idRaca);
            racaRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new RacaNaoEncontradaException(idRaca);
        }
    }

}
