package br.uema.loja_Roupas.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Pedidos")
@NoArgsConstructor
@Getter
@Setter
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Pedido", nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "Data_Pedido")
    private LocalDate data;

    // Força a data de hoje ao persistir no banco
    @PrePersist
    public void definirDataAtual() {
        this.data = LocalDate.now();
    }

    @ManyToOne
    @JoinColumn(name = "ID_Cliente")
    private Cliente cliente;

    @Column(name = "Status_Pedido")
    private String status;

    @Builder
    public Pedido(LocalDate data, Cliente cliente, String status) {
        this.data = data;
        this.cliente = cliente;
        this.status = status;
    }

}
