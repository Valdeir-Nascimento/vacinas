package br.edu.ufra.vacinas.api.controller;

import java.util.List;

import br.edu.ufra.vacinas.api.dto.converter.VacinaConverter;
import br.edu.ufra.vacinas.api.util.ResourceUriUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufra.vacinas.api.dto.VacinaDTO;
import br.edu.ufra.vacinas.api.dto.request.VacinaRequest;
import br.edu.ufra.vacinas.api.model.Animal;
import br.edu.ufra.vacinas.api.model.Vacina;
import br.edu.ufra.vacinas.api.service.AnimalService;
import br.edu.ufra.vacinas.api.service.VacinaService;


@RestController
@RequestMapping(value = "/animais/{idAnimal}/vacinas")
public class VacinaController {
    @Autowired
    private VacinaService vacinaService;
    @Autowired
    private AnimalService animalService;
    @Autowired
    private VacinaConverter vacinaConverter;

    @GetMapping
    public ResponseEntity<List<VacinaDTO>> listar(@PathVariable Integer idAnimal) {
        Animal animalAtual = animalService.buscar(idAnimal);
        List<Vacina> vacinaList = vacinaService.obter(animalAtual);
        return ResponseEntity.ok().body(vacinaConverter.to(vacinaList));
    }

    @GetMapping("/{idVacina}")
    public ResponseEntity<VacinaDTO> buscar(@PathVariable Integer idAnimal, @PathVariable Integer idVacina) {
        Vacina vacinaAtual = vacinaService.buscar(idVacina);
        return ResponseEntity.ok().body(vacinaConverter.to(vacinaAtual));
    }

    @PostMapping
    public ResponseEntity<VacinaDTO> salvar(@PathVariable Integer idAnimal, @RequestBody VacinaRequest vacinaRequest) {
        Vacina vacina = vacinaConverter.to(vacinaRequest);
        vacina = vacinaService.salvar(vacina);
        ResourceUriUtil.addUriInResponseHeader(vacina.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(vacinaConverter.to(vacina));
    }

    @PutMapping("/{idVacina}")
    public ResponseEntity<VacinaDTO> atualizar(@PathVariable Integer idAnimal, @PathVariable Integer idVacina, @RequestBody VacinaRequest vacinaRequest) {
        Vacina vacinaAtual = vacinaService.buscar(idVacina);
        vacinaConverter.copyToProperties(vacinaRequest, vacinaAtual);
        vacinaAtual = vacinaService.salvar(vacinaAtual);
        return ResponseEntity.ok().body(vacinaConverter.to(vacinaAtual));
    }

    @DeleteMapping("/{idVacina}")
    public void excluir(@PathVariable Integer idAnimal, @PathVariable Integer idVacina) {
        vacinaService.excluir(idVacina);
    }

}
