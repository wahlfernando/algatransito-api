package com.algaworks.faw.algatransito.domain.service;

import com.algaworks.faw.algatransito.domain.exception.NegocioException;
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
        boolean emailEmUso = proprrietarioRepository
            .findByEmail(proprietario.getEmail())
            .filter(p -> !p.equals(proprietario))
            .isPresent();

        if (emailEmUso) {
            throw new NegocioException("Email ja está em uso!");
        }

        return proprrietarioRepository.save(proprietario);
    }

    @Transactional
    public void excluir(Long proprietarioId) {
        proprrietarioRepository.deleteById(proprietarioId);
    }
}
