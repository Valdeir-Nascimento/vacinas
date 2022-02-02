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
import org.springframework.web.bind.annotation.RestController;

import br.edu.ufra.vacinas.api.dto.VacinaDTO;
import br.edu.ufra.vacinas.api.dto.converter.VacinaDTOConverter;
import br.edu.ufra.vacinas.api.dto.converter.VacinaRequestConverter;
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
    private VacinaDTOConverter vacinaDTOConverter;
    @Autowired
    private VacinaRequestConverter vacinaRequestConverter;
    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public ResponseEntity<List<VacinaDTO>> findAll(@PathVariable Integer idAnimal) {
        Animal animalAtual = animalService.findById(idAnimal);
        List<Vacina> vacinaList = vacinaService.obter(animalAtual);
        return ResponseEntity.ok().body(vacinaDTOConverter.to(vacinaList));
    }

    @GetMapping("/{idVacina}")
    public ResponseEntity<VacinaDTO> findById(@PathVariable Integer idAnimal, @PathVariable Integer idVacina) {
        Vacina vacinaAtual = vacinaService.findById(idVacina);
        return ResponseEntity.ok().body(vacinaDTOConverter.to(vacinaAtual));
    }

    @PostMapping
    public ResponseEntity<VacinaDTO> criar(
            @PathVariable Integer idAnimal,
            @RequestBody VacinaRequest vacinaRequest,
            HttpServletResponse response
    ) {
        Vacina vacina = vacinaRequestConverter.to(vacinaRequest);
        vacina = vacinaService.salvar(vacina);
        return ResponseEntity.status(HttpStatus.CREATED).body(vacinaDTOConverter.to(vacina));
    }

    @PutMapping("/{idVacina}")
    public ResponseEntity<VacinaDTO> atualizar(
            @PathVariable Integer idAnimal,
            @PathVariable Integer idVacina,
            @RequestBody VacinaRequest vacinaRequest) {
        Vacina vacinaAtual = vacinaService.findById(idVacina);
        vacinaRequestConverter.copyToProperties(vacinaRequest, vacinaAtual);
        vacinaAtual = vacinaService.salvar(vacinaAtual);
        return ResponseEntity.ok().body(vacinaDTOConverter.to(vacinaAtual));
    }

    @DeleteMapping("/{idVacina}")
    public void excluir(@PathVariable Integer idAnimal, @PathVariable Integer idVacina) {
        vacinaService.excluir(idVacina);
    }

}
