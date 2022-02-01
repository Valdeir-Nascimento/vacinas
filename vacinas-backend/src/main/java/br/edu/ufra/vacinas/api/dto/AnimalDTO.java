package br.edu.ufra.vacinas.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class AnimalDTO {
    private Integer id;
    private String nome;
    private String dono;
    private String telefone;
    private Character tipo;
    private Date nascimento;
}
