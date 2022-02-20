package br.edu.ufra.vacinas.api.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
public class VacinaRequest {

    @NotBlank
    @Size(min = 1, max = 100)
    private String nome;

    @Valid
    private AnimalIdRequest animal;
}
