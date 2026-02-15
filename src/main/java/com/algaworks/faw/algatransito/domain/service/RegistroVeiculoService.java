package com.algaworks.faw.algatransito.domain.service;

import com.algaworks.faw.algatransito.domain.exception.NegocioException;
import com.algaworks.faw.algatransito.domain.model.Proprietario;
import com.algaworks.faw.algatransito.domain.model.StatusVeiculo;
import com.algaworks.faw.algatransito.domain.model.Veiculo;
import com.algaworks.faw.algatransito.domain.repository.VeiculoRepositorio;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@AllArgsConstructor
@Service
public class RegistroVeiculoService {

    private VeiculoRepositorio veiculoRepositorio;
    private RegistroProprietarioService registroProprietarioService;

    @Transactional
    public Veiculo cadastrar(Veiculo veiculo) {
        if (veiculo.getId() != null) {
            throw new NegocioException("Veículo a ser cadastrado não deve ser ...");
        }

        boolean placaEmUso = veiculoRepositorio
            .findByPlaca(veiculo.getPlaca())
            .filter(p -> !p.equals(veiculo))
            .isPresent();

        if (placaEmUso) {
            throw new NegocioException("Ja existe um veículo com essa placa cadastrada");
        }

        Proprietario proprietario = registroProprietarioService.buscar(veiculo.getProprietario().getId());

        veiculo.setProprietario(proprietario);
        veiculo.setStatus(StatusVeiculo.REGULAR);
        veiculo.setDataCadastro(LocalDateTime.now());
        return veiculoRepositorio.save(veiculo);
    }
}
