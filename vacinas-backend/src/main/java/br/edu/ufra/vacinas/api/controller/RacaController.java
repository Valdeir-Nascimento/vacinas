package br.edu.ufra.vacinas.api.controller;

import br.edu.ufra.vacinas.api.dto.RacaDTO;
import br.edu.ufra.vacinas.api.dto.converter.RacaConverter;
import br.edu.ufra.vacinas.api.dto.request.RacaRequest;
import br.edu.ufra.vacinas.api.model.Raca;
import br.edu.ufra.vacinas.api.service.RacaService;
import br.edu.ufra.vacinas.api.util.ResourceUriUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value ="/racas")
public class RacaController {

    @Autowired
    private RacaService racaService;
    @Autowired
    private RacaConverter racaConverter;

    @GetMapping
    public ResponseEntity<List<RacaDTO>> listar() {
        List<Raca> racaList = racaService.listar();
        return ResponseEntity.ok().body(racaConverter.to(racaList));
    }

    @GetMapping("/{idRaca}")
    public ResponseEntity<RacaDTO> buscar(@PathVariable Integer idRaca) {
        Raca racaAtual = racaService.buscar(idRaca);
        return ResponseEntity.ok().body(racaConverter.to(racaAtual));
    }

    @PostMapping
    public ResponseEntity<RacaDTO> salvar(@RequestBody RacaRequest racaRequest) {
        Raca raca = racaConverter.to(racaRequest);
        raca = racaService.salvar(raca);
        ResourceUriUtil.addUriInResponseHeader(raca.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(racaConverter.to(raca));
    }

    @PutMapping("/{idRaca}")
    public ResponseEntity<RacaDTO> atualizar(@PathVariable Integer idRaca, @RequestBody RacaRequest racaRequest) {
        Raca racaAtual = racaService.buscar(idRaca);
        racaConverter.copyToProperties(racaRequest, racaAtual);
        racaAtual = racaService.salvar(racaAtual);
        return ResponseEntity.ok().body(racaConverter.to(racaAtual));
    }

    @DeleteMapping("/{idRaca}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Integer idRaca) {
        racaService.excluir(idRaca);
    }

}
