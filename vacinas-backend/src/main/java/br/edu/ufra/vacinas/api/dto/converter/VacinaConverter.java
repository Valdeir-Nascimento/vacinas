package br.edu.ufra.vacinas.api.dto.converter;

import br.edu.ufra.vacinas.api.dto.VacinaDTO;
import br.edu.ufra.vacinas.api.dto.request.VacinaRequest;
import br.edu.ufra.vacinas.api.model.Vacina;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VacinaConverter {

    @Autowired
    private ModelMapper mapper;

    public VacinaDTO to(Vacina vacina) {
        return mapper.map(vacina, VacinaDTO.class);
    }

    public Vacina to(VacinaRequest request) {
        return mapper.map(request, Vacina.class);
    }

    public List<VacinaDTO> to(List<Vacina> list) {
        return list.stream()
                .map(this::to)
                .collect(Collectors.toList());
    }

    public void copyToProperties(VacinaRequest request, Vacina vacina) {
        mapper.map(request, vacina);
    }

}
