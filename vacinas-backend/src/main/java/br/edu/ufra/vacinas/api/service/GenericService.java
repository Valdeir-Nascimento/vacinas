package br.edu.ufra.vacinas.api.service;

import br.edu.ufra.vacinas.api.model.BaseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GenericService<T extends BaseEntity> {

    List<T> findAll();
    T save(T entity);
    T findById(Integer id);
    void delete(Integer id);

}
