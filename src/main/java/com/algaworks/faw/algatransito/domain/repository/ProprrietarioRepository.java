package com.algaworks.faw.algatransito.domain.repository;

import com.algaworks.faw.algatransito.domain.model.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProprrietarioRepository extends JpaRepository<Proprietario, Long> {

    List<Proprietario> findByNome(String nome);

    List<Proprietario> findByNomeContaining(String nome);

    Optional<Proprietario> findByEmail(String email);
}
