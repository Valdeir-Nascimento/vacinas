package br.edu.ufra.vacinas.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufra.vacinas.api.dto.AnimalDTO;
import br.edu.ufra.vacinas.api.dto.converter.AnimalDTOConverter;
import br.edu.ufra.vacinas.api.dto.converter.AnimalRequestConverter;
import br.edu.ufra.vacinas.api.dto.request.AnimalRequest;
import br.edu.ufra.vacinas.api.model.Animal;
import br.edu.ufra.vacinas.api.service.AnimalService;


@RestController
@RequestMapping(value ="/animais")
public class AnimalController {

    @Autowired
    private AnimalService animalService;
    @Autowired
    private AnimalDTOConverter animalDTOConverter;
    @Autowired
    private AnimalRequestConverter animalRequestConverter;
    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public ResponseEntity<List<AnimalDTO>> findAll() {
        List<Animal> animalList = animalService.obterAnimais();
        return ResponseEntity.ok().body(animalDTOConverter.to(animalList));
    }

    @PostMapping
    public ResponseEntity<AnimalDTO> salvar(@RequestBody AnimalRequest animalRequest, HttpServletResponse response){
        Animal animal = animalRequestConverter.to(animalRequest);
        animal = animalService.salvar(animal);
       
        return ResponseEntity.status(HttpStatus.CREATED).body(animalDTOConverter.to(animal));
    }

    @GetMapping("/{idAnimal}")
    public ResponseEntity<AnimalDTO> findById(@PathVariable Integer idAnimal) {
        Animal animal = animalService.findById(idAnimal);
        return ResponseEntity.ok().body(animalDTOConverter.to(animal));
    }

    @PutMapping("/{idAnimal}")
    public ResponseEntity<AnimalDTO> atualizar(@PathVariable Integer idAnimal, @RequestBody AnimalRequest animalRequest) {
        Animal animalAtual = animalService.findById(idAnimal);
        animalRequestConverter.copyToProperties(animalRequest, animalAtual);
        animalAtual = animalService.salvar(animalAtual);
        return ResponseEntity.ok().body(animalDTOConverter.to(animalAtual));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{idAnimal}")
    public void excluir(@PathVariable Integer idAnimal) {
        animalService.excluir(idAnimal);
    }

}
