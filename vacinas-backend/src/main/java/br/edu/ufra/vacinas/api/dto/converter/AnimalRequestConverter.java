package br.edu.ufra.vacinas.api.dto.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.edu.ufra.vacinas.api.dto.request.AnimalRequest;
import br.edu.ufra.vacinas.api.model.Animal;

@Component
public class AnimalRequestConverter {

    @Autowired
    private ModelMapper modelMapper;

    public Animal to(AnimalRequest animalRequest) {
        return modelMapper.map(animalRequest, Animal.class);
    }

    public void copyToProperties(AnimalRequest animalRequest, Animal animal) {
        modelMapper.map(animalRequest, animal);
    }

}
