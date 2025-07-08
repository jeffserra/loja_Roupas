package br.uema.loja_Roupas.repository;

import br.uema.loja_Roupas.entity.Estoque;
import br.uema.loja_Roupas.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<Estoque, Integer> {
    Estoque findByProduto(Produto produto);
}
