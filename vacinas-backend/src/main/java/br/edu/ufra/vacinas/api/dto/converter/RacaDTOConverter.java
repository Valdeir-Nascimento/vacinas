package br.edu.ufra.vacinas.api.dto.converter;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ufra.vacinas.api.dto.RacaDTO;
import br.edu.ufra.vacinas.api.model.Raca;

@Component
public class RacaDTOConverter {

    @Autowired
    private ModelMapper modelMapper;

    public RacaDTO to(Raca raca) {
        return modelMapper.map(raca, RacaDTO.class);
    }

    public List<RacaDTO> to(List<Raca> racaList) {
        return racaList.stream()
                .map(raca -> to(raca))
                .collect(Collectors.toList());
    }

}
