package br.uema.loja_Roupas.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Itens_Pedido_Promocao")
@IdClass(ItensPedidoPromocaoId.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItensPedidoPromocao {

    @Id
    @ManyToOne
    @JoinColumn(name = "ID_Item")
    private ItensPedido item;

    @Id
    @ManyToOne
    @JoinColumn(name = "ID_Promocao")
    private Promocao promocao;
}
