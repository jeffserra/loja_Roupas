package br.uema.loja_Roupas.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Promocoes_Produtos")
@NoArgsConstructor
@Getter
@Setter
public class PromocaoProduto {

    @EmbeddedId
    private PromocaoProdutoId id;

    @ManyToOne
    @MapsId("idPromocao")
    @JoinColumn(name = "ID_Promocao")
    private Promocao promocao;

    @ManyToOne
    @MapsId("idProduto")
    @JoinColumn(name = "ID_Produto")
    private Produto produto;

    @Builder
    public PromocaoProduto(PromocaoProdutoId id, Promocao promocao, Produto produto) {
        this.id = new PromocaoProdutoId(promocao.getId(), produto.getId());
        this.promocao = promocao;
        this.produto = produto;
    }

}
