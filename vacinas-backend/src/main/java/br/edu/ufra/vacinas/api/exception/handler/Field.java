package br.edu.ufra.vacinas.api.exception.handler;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Field {

    private String name;
    private String userMessage;

}
