package br.edu.ufra.vacinas.api.controller.exceptionhandler;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Field {

    private String name;
    private String userMessage;

}
