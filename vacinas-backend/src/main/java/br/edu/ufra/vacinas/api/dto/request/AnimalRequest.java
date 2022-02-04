package br.edu.ufra.vacinas.api.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Setter
@Getter
public class AnimalRequest {

    @NotBlank
    @Size(min = 1, max = 100)
    private String nome;

    @NotBlank
    @Size(min = 1, max = 100)
    private String dono;

    @NotBlank
    @Size(min = 1, max = 9)
    private String telefone;

    @NotNull
    private Character tipo;

    @NotNull
    private Date nascimento;

    @Valid
    private RacaRequest raca;
}
