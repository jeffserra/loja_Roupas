package br.uema.loja_Roupas.repository;

import br.uema.loja_Roupas.entity.ItensPedido;
import br.uema.loja_Roupas.entity.ItensPedidoPromocao;
import br.uema.loja_Roupas.entity.ItensPedidoPromocaoId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItensPedidoPromocaoRepository extends JpaRepository<ItensPedidoPromocao, ItensPedidoPromocaoId> {
    Optional<ItensPedidoPromocao> findByItem(ItensPedido item);
}
