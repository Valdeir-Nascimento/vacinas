package br.edu.ufra.vacinas.api.repository;

import br.edu.ufra.vacinas.api.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenericRepository<T extends BaseEntity> extends JpaRepository<T, Integer> {
}
