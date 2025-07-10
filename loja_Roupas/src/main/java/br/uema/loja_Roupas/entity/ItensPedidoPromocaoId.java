package br.uema.loja_Roupas.entity;

import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItensPedidoPromocaoId implements Serializable {

    private ItensPedido item;
    private Promocao promocao;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItensPedidoPromocaoId that)) return false;
        return Objects.equals(item, that.item) &&
                Objects.equals(promocao, that.promocao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, promocao);
    }
}
