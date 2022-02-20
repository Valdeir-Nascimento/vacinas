package br.edu.ufra.vacinas.api.dto.request;

import lombok.Setter;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Setter
@Getter
public class AnimalIdRequest {

    @NotNull
    @Positive
    private Integer id;

}
