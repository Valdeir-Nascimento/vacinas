package br.edu.ufra.vacinas.api.service.impl;

import br.edu.ufra.vacinas.api.exception.EntidadeNaoEncontradaException;
import br.edu.ufra.vacinas.api.model.BaseEntity;
import br.edu.ufra.vacinas.api.repository.GenericRepository;
import br.edu.ufra.vacinas.api.service.GenericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static br.edu.ufra.vacinas.api.util.MessagesUtil.MSG_REGISTRO_NAO_ENCONTRADO;

public class GenericServiceImpl<T extends BaseEntity> implements GenericService<T> {

    @Autowired
    private GenericRepository<T> genericRepository;

    @Transactional(readOnly = true)
    @Override
    public List<T> findAll() {
        return genericRepository.findAll();
    }

    @Transactional
    @Override
    public T save(T entity) {
        return genericRepository.save(entity);
    }

    @Override
    public T findById(Integer id) {
        return genericRepository
                .findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(String.format(MSG_REGISTRO_NAO_ENCONTRADO, id)));
    }

    @Override
    public void delete(Integer id) {
        try {
            genericRepository.deleteById(id);
            genericRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntidadeNaoEncontradaException(String.format(MSG_REGISTRO_NAO_ENCONTRADO, id));
        }
    }

}
