package com.algaworks.faw.algatransito.api.controller;

import com.algaworks.faw.algatransito.api.assembler.VeiculoAssembler;
import com.algaworks.faw.algatransito.api.model.VeiculoModel;
import com.algaworks.faw.algatransito.api.model.input.VeiculoInput;
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
    private VeiculoAssembler veiculoAssembler;

    @GetMapping
    public List<VeiculoModel> listar() {
        return veiculoAssembler.toCollectionModel(veiculoRepositorio.findAll());
    }

    @GetMapping("{veiculoId}")
    public ResponseEntity<VeiculoModel> buscar(@PathVariable Long veiculoId) {
        return veiculoRepositorio.findById(veiculoId)
                                 .map(veiculo -> veiculoAssembler.toModel(veiculo))
                                 .map(ResponseEntity::ok)
                                 .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VeiculoModel cadastar(@Valid @RequestBody VeiculoInput veiculoInput) {
        return veiculoAssembler.toModel(
            registroVeiculoService.cadastrar(
                veiculoAssembler.toEntity(veiculoInput)
            )
        );
    }
}
