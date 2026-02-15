package com.algaworks.faw.algatransito.api.controller;

import com.algaworks.faw.algatransito.domain.exception.NegocioException;
import com.algaworks.faw.algatransito.domain.model.Proprietario;
import com.algaworks.faw.algatransito.domain.repository.ProprrietarioRepository;
import com.algaworks.faw.algatransito.domain.service.RegistroProprietarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/proprietarios")
public class ProprietarioController {

    private ProprrietarioRepository proprrietarioRepository;
    private RegistroProprietarioService registroProprietarioService;

    @GetMapping()
    public List<Proprietario> listar() {
        return proprrietarioRepository.findAll();
    }

    @GetMapping("{proprietarioId}")
    public ResponseEntity<Proprietario> buscar(@PathVariable Long proprietarioId) {
        return proprrietarioRepository.findById(proprietarioId)
                                      .map(ResponseEntity::ok)
                                      .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Proprietario adicionar(@Valid @RequestBody Proprietario proprietario) {
        return registroProprietarioService.salvar(proprietario);
    }

    @PutMapping("{proprietarioId}")
    public ResponseEntity<Proprietario> atualizar(@Valid @PathVariable Long proprietarioId, @RequestBody Proprietario proprietario) {
        if (!proprrietarioRepository.existsById(proprietarioId)) {
            return ResponseEntity.notFound().build();
        }
        proprietario.setId(proprietarioId);
        Proprietario proprietarioAtualizado = registroProprietarioService.salvar(proprietario);
        return ResponseEntity.ok(proprietarioAtualizado);
    }

    @DeleteMapping("{proprietarioId}")
    public ResponseEntity<Void> remover(@PathVariable Long proprietarioId) {
        if (!proprrietarioRepository.existsById(proprietarioId)) {
            return ResponseEntity.notFound().build();
        }
        registroProprietarioService.excluir(proprietarioId);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<String> capturar(NegocioException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
