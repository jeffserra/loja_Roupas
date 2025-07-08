package br.uema.loja_Roupas.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.math.BigDecimal;


@Entity
@Table(name = "Promocoes")
@NoArgsConstructor
@Getter
@Setter
public class Promocao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Promocao", nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    private Integer id;

    @Column(name = "Nome_Promocao")
    private String nome;

    @Column(name = "Desconto_Percentual")
    private BigDecimal desconto;

    @Column(name = "Data_Inicio")
    private LocalDate data_inicio;

    @Column(name = "Data_Fim")
    private LocalDate data_fim;

    @Builder
    public Promocao(String nome, BigDecimal desconto, LocalDate data_inicio, LocalDate data_fim) {
        this.nome = nome;
        this.desconto = desconto;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
    }

}
