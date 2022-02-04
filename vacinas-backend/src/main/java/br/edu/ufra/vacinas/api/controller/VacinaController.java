package br.edu.ufra.vacinas.api.controller;

import br.edu.ufra.vacinas.api.dto.VacinaDTO;
import br.edu.ufra.vacinas.api.dto.converter.VacinaConverter;
import br.edu.ufra.vacinas.api.dto.request.VacinaRequest;
import br.edu.ufra.vacinas.api.model.Vacina;
import br.edu.ufra.vacinas.api.service.AnimalService;
import br.edu.ufra.vacinas.api.service.VacinaService;
import br.edu.ufra.vacinas.api.util.ResourceUriUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


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
        List<Vacina> vacinas = vacinaService.obter(animalService.buscar(idAnimal));
        return ResponseEntity.ok().body(vacinaConverter.to(vacinas));
    }

    @GetMapping("/{idVacina}")
    public ResponseEntity<VacinaDTO> buscar(@PathVariable Integer idAnimal, @PathVariable Integer idVacina) {
        Vacina vacinaAtual = vacinaService.buscar(idAnimal, idVacina);
        return ResponseEntity.ok().body(vacinaConverter.to(vacinaAtual));
    }

    @PostMapping
    public ResponseEntity<VacinaDTO> salvar(@PathVariable Integer idAnimal, @Valid @RequestBody VacinaRequest vacinaRequest) {
        Vacina vacina = vacinaConverter.to(vacinaRequest);
        vacina = vacinaService.salvar(vacina);
        ResourceUriUtil.addUriInResponseHeader(vacina.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(vacinaConverter.to(vacina));
    }

    @PutMapping("/{idVacina}")
    public ResponseEntity<VacinaDTO> atualizar(@PathVariable Integer idAnimal, @PathVariable Integer idVacina, @Valid @RequestBody VacinaRequest vacinaRequest) {
        Vacina vacinaAtual = vacinaService.buscar(idAnimal, idVacina);
        vacinaConverter.copyToProperties(vacinaRequest, vacinaAtual);
        vacinaAtual = vacinaService.salvar(vacinaAtual);
        return ResponseEntity.ok().body(vacinaConverter.to(vacinaAtual));
    }

    @DeleteMapping("/{idVacina}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Integer idAnimal, @PathVariable Integer idVacina) {
        vacinaService.excluir(idVacina);
    }

}
