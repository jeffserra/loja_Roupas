package br.uema.loja_Roupas.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Produtos")
@NoArgsConstructor
@Getter
@Setter
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Produto", nullable = false, unique = true)
    @Setter(AccessLevel.NONE)
    private Integer id;

    @Column(name = "Nome_Produto")
    private String nome;

    @Column(name = "Descricao")
    private String descricao;

    @Column(name = "Preco")
    private Double preco;

    @Column(name = "Categoria")
    private String categoria;

    @Column(name = "Tamanho")
    private String tamanho;

    @Column(name = "Cor")
    private String cor;

    @Column(name = "Genero")
    private String genero;

    @ManyToOne
    @JoinColumn(name = "ID_Marca")
    private Marca marca;

    @Builder
    public Produto(String nome, String descricao, Double preco, String categoria, String tamanho, String cor, String genero, Marca marca) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
        this.tamanho = tamanho;
        this.cor = cor;
        this.genero = genero;
        this.marca = marca;
    }

}
