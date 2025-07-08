package br.uema.loja_Roupas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.math.BigDecimal;

@Entity
@Table(name = "Registros_Pagamentos")
@NoArgsConstructor
@Getter
@Setter
public class RegistroPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Pagamento", nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_Pedido")
    private Pedido pedido;

    @Column(name = "Metodo_Pagamento")
    private String metodo;

    @Column(name = "Valor_Pagamento")
    private BigDecimal valor;

    @Column(name = "Data_Pagamento")
    private LocalDate data;

    @Builder
    public RegistroPagamento(Pedido pedido, String metodo, BigDecimal valor, LocalDate data) {
        this.pedido = pedido;
        this.metodo = metodo;
        this.valor = valor;
        this.data = data;
    }

}
