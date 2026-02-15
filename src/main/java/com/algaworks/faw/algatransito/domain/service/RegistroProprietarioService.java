package com.algaworks.faw.algatransito.domain.service;

import com.algaworks.faw.algatransito.domain.model.Proprietario;
import com.algaworks.faw.algatransito.domain.repository.ProprrietarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class RegistroProprietarioService {

    private final ProprrietarioRepository proprrietarioRepository;

    @Transactional
    public Proprietario salvar(Proprietario proprietario) {
        return proprrietarioRepository.save(proprietario);
    }

    @Transactional
    public void excluir(Long proprietarioId) {
        proprrietarioRepository.deleteById(proprietarioId);
    }
}
