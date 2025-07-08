package br.uema.loja_Roupas.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Estoque")
@NoArgsConstructor
@Getter
@Setter
public class Estoque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Estoque", nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_Produto")
    private Produto produto;

    @Column(name = "Quantidade_Em_Estoque")
    private Integer quantidade;

    @Builder
    public Estoque(Produto produto, Integer quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

}
