package br.edu.ufra.vacinas.api.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VacinaDTO {

    private Integer id;
    private String nome;
    private Date data;
    private AnimalDTO animal;
}
