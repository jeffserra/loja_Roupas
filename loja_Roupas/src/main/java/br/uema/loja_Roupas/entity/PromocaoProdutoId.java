package br.uema.loja_Roupas.entity;

import jakarta.persistence.Embeddable;
import lombok.*;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class PromocaoProdutoId implements Serializable {

    private Integer idPromocao;
    private Integer idProduto;

    @Builder
    public PromocaoProdutoId(Integer idPromocao, Integer idProduto) {
        this.idPromocao = idPromocao;
        this.idProduto = idProduto;
    }

}

