package br.edu.ufra.vacinas.api.dto.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ufra.vacinas.api.dto.request.RacaRequest;
import br.edu.ufra.vacinas.api.model.Raca;

@Component
public class RacaRequestConverter {

    @Autowired
    private ModelMapper modelMapper;

    public Raca to(RacaRequest racaRequest) {
        return modelMapper.map(racaRequest, Raca.class);
    }

    public void copyToProperties(RacaRequest racaRequest, Raca raca) {
        modelMapper.map(racaRequest, raca);
    }

}
