package br.uema.loja_Roupas.service;

import br.uema.loja_Roupas.entity.*;
import br.uema.loja_Roupas.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired private PedidoRepository repo;

    public List<Pedido> listarTodos() {
        return repo.findAll();
    }

    public Optional<Pedido> buscarPorId(Integer id) {
        return repo.findById(id);
    }

    public Pedido salvar(Pedido pedido) {
        pedido.setStatus("Pendente");
        return repo.save(pedido);
    }

    public Pedido atualizar(Integer id, Pedido novoPedido) {
        Pedido pedido = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com ID: " + id));

        pedido.setData(novoPedido.getData());
        pedido.setStatus(novoPedido.getStatus());
        pedido.setCliente(novoPedido.getCliente());

        return repo.save(pedido);
    }

    public void deletar(Integer id) {
        repo.deleteById(id);
    }

}
