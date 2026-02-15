package com.algaworks.faw.algatransito.domain.repository;

import com.algaworks.faw.algatransito.domain.model.Proprietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProprrietarioRepository extends JpaRepository<Proprietario, Long> {

    List<Proprietario> findByNome(String nome);
    List<Proprietario> findByNomeContaining(String nome);

}
