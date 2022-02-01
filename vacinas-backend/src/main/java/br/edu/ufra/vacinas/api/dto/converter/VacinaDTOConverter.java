package br.edu.ufra.vacinas.api.dto.converter;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ufra.vacinas.api.dto.VacinaDTO;
import br.edu.ufra.vacinas.api.model.Vacina;

@Component
public class VacinaDTOConverter {

    @Autowired
    private ModelMapper modelMapper;

    public VacinaDTO to(Vacina vacina) {
        return modelMapper.map(vacina, VacinaDTO.class);
    }

    public List<VacinaDTO> to(List<Vacina> vacinaList) {
        return vacinaList.stream()
                .map(vacina -> to(vacina))
                .collect(Collectors.toList());
    }

}
