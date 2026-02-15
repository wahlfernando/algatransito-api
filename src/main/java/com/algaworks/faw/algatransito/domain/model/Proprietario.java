package com.algaworks.faw.algatransito.domain.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "proprietario")
public class Proprietario {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(max = 60)
    @NotBlank
    @Column
    private String nome;

    @Size(max = 255)
    @Column
    private String email;

    @Column(name = "fone")
    private String telefone;
}
