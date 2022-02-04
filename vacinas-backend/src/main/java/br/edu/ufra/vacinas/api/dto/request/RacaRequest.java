package br.edu.ufra.vacinas.api.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
public class RacaRequest {

    @NotBlank
    @Size(min = 1, max = 50)
    private String nome;
}
