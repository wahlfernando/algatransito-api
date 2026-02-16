package com.algaworks.faw.algatransito.api.controller;

import com.algaworks.faw.algatransito.domain.model.Veiculo;
import com.algaworks.faw.algatransito.domain.repository.VeiculoRepositorio;
import com.algaworks.faw.algatransito.domain.service.RegistroVeiculoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private VeiculoRepositorio veiculoRepositorio;
    private RegistroVeiculoService registroVeiculoService;

    @GetMapping
    public List<Veiculo> listar() {
        return veiculoRepositorio.findAll();
    }

    @GetMapping("{veiculoId}")
    public ResponseEntity<Veiculo> busca(@PathVariable Long veiculoId) {
        return veiculoRepositorio.findById(veiculoId)
                                 .map(ResponseEntity::ok)
                                 .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Veiculo cadastar(@Valid @RequestBody Veiculo veiculo) {
        return registroVeiculoService.cadastrar(veiculo);
    }
}
