package br.uema.loja_Roupas.repository;

import br.uema.loja_Roupas.entity.ItensPedido;
import br.uema.loja_Roupas.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ItensPedidoRepository extends JpaRepository<ItensPedido, Integer> {
    List<ItensPedido> findByPedido(Pedido pedido);
}
