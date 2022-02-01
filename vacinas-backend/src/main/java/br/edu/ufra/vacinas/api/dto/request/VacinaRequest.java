package br.edu.ufra.vacinas.api.dto.request;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class VacinaRequest {
    private String nome;
    private Date data;
    private AnimalRequest animal;
}
