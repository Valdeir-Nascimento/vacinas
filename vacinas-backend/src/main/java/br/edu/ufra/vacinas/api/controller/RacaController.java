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

import br.edu.ufra.vacinas.api.dto.RacaDTO;
import br.edu.ufra.vacinas.api.dto.converter.RacaDTOConverter;
import br.edu.ufra.vacinas.api.dto.converter.RacaRequestConverter;
import br.edu.ufra.vacinas.api.dto.request.RacaRequest;
import br.edu.ufra.vacinas.api.model.Raca;
import br.edu.ufra.vacinas.api.service.RacaService;


@RestController
@RequestMapping(value ="/racas")
public class RacaController {

    @Autowired
    private RacaService racaService;
    @Autowired
    private RacaDTOConverter racaDTOConverter;
    @Autowired
    private RacaRequestConverter racaRequestConverter;
    @Autowired
    private ApplicationEventPublisher publisher;

    @GetMapping
    public ResponseEntity<List<RacaDTO>> findAll() {
        List<Raca> racaList = racaService.listar();
        return ResponseEntity.ok().body(racaDTOConverter.to(racaList));
    }

    @GetMapping("/{idRaca}")
    public ResponseEntity<RacaDTO> findById(@PathVariable Integer idRaca) {
        Raca racaAtual = racaService.findById(idRaca);
        return ResponseEntity.ok().body(racaDTOConverter.to(racaAtual));
    }

    @PostMapping
    public ResponseEntity<RacaDTO> criar(@RequestBody RacaRequest racaRequest, HttpServletResponse response) {
        Raca raca = racaRequestConverter.to(racaRequest);
        raca = racaService.salvar(raca);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(racaDTOConverter.to(raca));
    }

    @PutMapping("/{idRaca}")
    public ResponseEntity<RacaDTO> atualizar(@PathVariable Integer idRaca, @RequestBody RacaRequest racaRequest) {
        Raca racaAtual = racaService.findById(idRaca);
        racaRequestConverter.copyToProperties(racaRequest, racaAtual);
        racaAtual = racaService.salvar(racaAtual);
        return ResponseEntity.ok().body(racaDTOConverter.to(racaAtual));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{idRaca}")
    public void excluir(@PathVariable Integer idRaca) {
        racaService.excluir(idRaca);
    }

}
