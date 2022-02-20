package br.edu.ufra.vacinas.api.controller;

import br.edu.ufra.vacinas.api.dto.RacaDTO;
import br.edu.ufra.vacinas.api.dto.converter.RacaConverter;
import br.edu.ufra.vacinas.api.dto.request.RacaRequest;
import br.edu.ufra.vacinas.api.model.Raca;
import br.edu.ufra.vacinas.api.service.impl.RacaServiceImpl;
import br.edu.ufra.vacinas.api.util.ResourceUriUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(value = "/racas")
public class RacaController {

    @Autowired
    private RacaServiceImpl racaService;
    @Autowired
    private RacaConverter racaConverter;

    @GetMapping
    public ResponseEntity<List<RacaDTO>> listar() {
        return ResponseEntity.ok().body(racaConverter.to(racaService.findAll()));
    }

    @GetMapping("/{idRaca}")
    public ResponseEntity<RacaDTO> buscar(@PathVariable Integer idRaca) {
        Raca raca = racaService.findById(idRaca);
        return ResponseEntity.ok().body(racaConverter.to(raca));
    }

    @PostMapping
    public ResponseEntity<RacaDTO> salvar(@Valid @RequestBody RacaRequest racaRequest) {
        Raca raca = racaConverter.to(racaRequest);
        raca = racaService.save(raca);
        ResourceUriUtil.addUriInResponseHeader(raca.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(racaConverter.to(raca));
    }

    @PutMapping("/{idRaca}")
    public ResponseEntity<RacaDTO> atualizar(@PathVariable Integer idRaca, @Valid @RequestBody RacaRequest racaRequest) {
        Raca racaAtual = racaService.findById(idRaca);
        racaConverter.copyToProperties(racaRequest, racaAtual);
        racaAtual = racaService.save(racaAtual);
        return ResponseEntity.ok().body(racaConverter.to(racaAtual));
    }

    @DeleteMapping("/{idRaca}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Integer idRaca) {
        racaService.delete(idRaca);
    }

}
