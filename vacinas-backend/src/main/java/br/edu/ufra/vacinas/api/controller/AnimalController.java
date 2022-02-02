package br.edu.ufra.vacinas.api.controller;

import br.edu.ufra.vacinas.api.dto.AnimalDTO;
import br.edu.ufra.vacinas.api.dto.converter.AnimalConverter;
import br.edu.ufra.vacinas.api.dto.request.AnimalRequest;
import br.edu.ufra.vacinas.api.model.Animal;
import br.edu.ufra.vacinas.api.service.AnimalService;
import br.edu.ufra.vacinas.api.util.ResourceUriUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RestController
@RequestMapping(value = "/animais")
public class AnimalController {

    @Autowired
    private AnimalService animalService;
    @Autowired
    private AnimalConverter animalConverter;


    @GetMapping
    public ResponseEntity<List<AnimalDTO>> listar() {
            List<Animal> animalList = animalService.listar();
        return ResponseEntity.ok().body(animalConverter.to(animalList));
    }

    @PostMapping
    public ResponseEntity<AnimalDTO> salvar(@RequestBody AnimalRequest animalRequest, HttpServletResponse response) {
        Animal animal = animalConverter.to(animalRequest);
        animal = animalService.salvar(animal);
        ResourceUriUtil.addUriInResponseHeader(animal.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(animalConverter.to(animal));
    }

    @GetMapping("/{idAnimal}")
    public ResponseEntity<AnimalDTO> buscar(@PathVariable Integer idAnimal) {
        Animal animal = animalService.buscar(idAnimal);
        return ResponseEntity.ok().body(animalConverter.to(animal));
    }

    @PutMapping("/{idAnimal}")
    public ResponseEntity<AnimalDTO> atualizar(@PathVariable Integer idAnimal, @RequestBody AnimalRequest animalRequest) {
        Animal animalAtual = animalService.buscar(idAnimal);
        animalConverter.copyToProperties(animalRequest, animalAtual);
        animalAtual = animalService.salvar(animalAtual);
        return ResponseEntity.ok().body(animalConverter.to(animalAtual));
    }

    @DeleteMapping("/{idAnimal}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Integer idAnimal) {
        animalService.excluir(idAnimal);
    }

}
