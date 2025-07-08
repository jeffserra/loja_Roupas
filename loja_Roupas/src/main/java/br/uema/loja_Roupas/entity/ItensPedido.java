package br.uema.loja_Roupas.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Itens_Pedido")
@NoArgsConstructor
@Getter
@Setter
public class ItensPedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Item", nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_Pedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "ID_Produto")
    private Produto produto;

    @Column(name = "Quantidade")
    private Integer quantidade;

    @Builder
    public ItensPedido(Pedido pedido, Produto produto, Integer quantidade) {
        this.pedido = pedido;
        this.produto = produto;
        this.quantidade = quantidade;
    }

}
