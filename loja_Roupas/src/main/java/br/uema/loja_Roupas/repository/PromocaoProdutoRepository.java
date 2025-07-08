package br.uema.loja_Roupas.repository;

import br.uema.loja_Roupas.entity.PromocaoProduto;
import br.uema.loja_Roupas.entity.PromocaoProdutoId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromocaoProdutoRepository extends JpaRepository<PromocaoProduto, PromocaoProdutoId> {

}