package br.edu.ufra.vacinas.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ufra.vacinas.api.model.Raca;

public interface RacaRepository extends JpaRepository<Raca, Integer> {
}
