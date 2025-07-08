package br.uema.loja_Roupas.repository;

import br.uema.loja_Roupas.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
