package br.edu.ufra.vacinas.api.dto.converter;

import br.edu.ufra.vacinas.api.dto.AnimalDTO;
import br.edu.ufra.vacinas.api.dto.request.AnimalRequest;
import br.edu.ufra.vacinas.api.model.Animal;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AnimalConverter {

    @Autowired
    private ModelMapper mapper;

    public AnimalDTO to(Animal animal) {
        return mapper.map(animal, AnimalDTO.class);
    }

    public Animal to(AnimalRequest request) {
        return mapper.map(request, Animal.class);
    }

    public List<AnimalDTO> to(List<Animal> list) {
        return list.stream()
                .map(this::to)
                .collect(Collectors.toList());
    }

    public void copyToProperties(AnimalRequest request, Animal animal) {
        mapper.map(request, animal);
    }

}
