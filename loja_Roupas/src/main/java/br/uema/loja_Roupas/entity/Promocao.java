package br.uema.loja_Roupas.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.List;


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

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "Data_Inicio")
    private LocalDate data_inicio;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @Column(name = "Data_Fim")
    private LocalDate data_fim;

    @ManyToMany
    @JoinTable(
            name = "Promocoes_Produtos",
            joinColumns = @JoinColumn(name = "ID_Promocao"),
            inverseJoinColumns = @JoinColumn(name = "ID_Produto")
    )
    @JsonIgnore
    private List<Produto> produtos;

    @Builder
    public Promocao(String nome, BigDecimal desconto, LocalDate data_inicio, LocalDate data_fim) {
        this.nome = nome;
        this.desconto = desconto;
        this.data_inicio = data_inicio;
        this.data_fim = data_fim;
    }

}
