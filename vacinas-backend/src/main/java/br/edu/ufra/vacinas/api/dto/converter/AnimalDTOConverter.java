package br.edu.ufra.vacinas.api.dto.converter;


import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ufra.vacinas.api.dto.AnimalDTO;
import br.edu.ufra.vacinas.api.model.Animal;

@Component
public class AnimalDTOConverter {

    @Autowired
    private ModelMapper modelMapper;

    public AnimalDTO to(Animal animal) {
        return modelMapper.map(animal, AnimalDTO.class);
    }

    public List<AnimalDTO> to(List<Animal> animalList) {
        return animalList.stream()
                .map(animal -> to(animal))
                .collect(Collectors.toList());
    }

}
