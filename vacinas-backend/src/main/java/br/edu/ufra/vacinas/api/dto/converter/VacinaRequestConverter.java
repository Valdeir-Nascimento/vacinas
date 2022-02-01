package br.edu.ufra.vacinas.api.dto.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ufra.vacinas.api.dto.request.VacinaRequest;
import br.edu.ufra.vacinas.api.model.Vacina;

@Component
public class VacinaRequestConverter {

    @Autowired
    private ModelMapper modelMapper;

    public Vacina to(VacinaRequest vacinaRequest) {
        return modelMapper.map(vacinaRequest, Vacina.class);
    }

    public void copyToProperties(VacinaRequest vacinaRequest, Vacina vacina) {
        modelMapper.map(vacinaRequest, vacina);
    }

}
