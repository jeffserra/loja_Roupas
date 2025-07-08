package br.uema.loja_Roupas.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Marcas")
@NoArgsConstructor
@Getter
@Setter
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Marca", nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    private Integer id;

    @Column(name = "Nome_Marca")
    private String nome;

    @Builder
    public Marca(String nome) {
        this.nome = nome;
    }

}
