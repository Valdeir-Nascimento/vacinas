package br.edu.ufra.vacinas.api.dto.converter;

import br.edu.ufra.vacinas.api.dto.RacaDTO;
import br.edu.ufra.vacinas.api.dto.request.RacaRequest;
import br.edu.ufra.vacinas.api.model.Raca;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RacaConverter {

    @Autowired
    private ModelMapper mapper;

    public RacaDTO to(Raca raca) {
        return mapper.map(raca, RacaDTO.class);
    }

    public Raca to(RacaRequest request) {
        return mapper.map(request, Raca.class);
    }

    public List<RacaDTO> to(List<Raca> list) {
        return list.stream()
                .map(this::to)
                .collect(Collectors.toList());
    }

    public void copyToProperties(RacaRequest request, Raca raca) {
        mapper.map(request, raca);
    }

}
