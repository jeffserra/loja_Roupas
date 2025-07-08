package br.uema.loja_Roupas.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Clientes")
@NoArgsConstructor
@Getter
@Setter
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Cliente", nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    private Integer id;

    @Column(name = "Nome_Cliente")
    private String nome;

    @Column(name = "Endereco")
    private String endereco;

    @Column(name = "Email")
    private String email;

    @Column(name = "Numero_Telefone")
    private String telefone;

    @Builder
    public Cliente(String nome, String endereco, String email, String telefone) {
        this.nome = nome;
        this.endereco = endereco;
        this.email = email;
        this.telefone = telefone;
    }

}
