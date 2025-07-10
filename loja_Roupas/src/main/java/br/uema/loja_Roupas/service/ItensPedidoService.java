package br.uema.loja_Roupas.service;

import br.uema.loja_Roupas.entity.*;
import br.uema.loja_Roupas.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItensPedidoService {

    @Autowired private ItensPedidoRepository repo;
    @Autowired private EstoqueRepository estoqueRepository;
    @Autowired private PedidoRepository pedidoRepository;

    public List<ItensPedido> listarTodos() {
        return repo.findAll();
    }

    public Optional<ItensPedido> buscarPorId(Integer id) {
        return repo.findById(id);
    }

    public ItensPedido salvar(ItensPedido item) {
        Pedido pedido = pedidoRepository.findById(item.getPedido().getId())
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        if ("Concluído".equalsIgnoreCase(pedido.getStatus())) {
            throw new RuntimeException("Não é possível adicionar itens a um pedido já concluído.");
        }

        Produto produto = item.getProduto();
        int quantidadeDesejada = item.getQuantidade();

        Estoque estoque = estoqueRepository.findByProduto(produto);

        if (estoque == null || estoque.getQuantidade() < quantidadeDesejada) {
            throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNome());
        }

        return repo.save(item);
    }

    public List<ItensPedido> salvarTodos(List<ItensPedido> itens) {
        if (itens.isEmpty()) {
            throw new RuntimeException("A lista de itens está vazia.");
        }

        Integer idPedido = itens.get(0).getPedido().getId();

        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        if ("Concluído".equalsIgnoreCase(pedido.getStatus())) {
            throw new RuntimeException("Não é possível adicionar itens a um pedido já concluído.");
        }

        for (ItensPedido item : itens) {
            Produto produto = item.getProduto();
            int quantidadeDesejada = item.getQuantidade();

            Estoque estoque = estoqueRepository.findByProduto(produto);

            if (estoque == null || estoque.getQuantidade() < quantidadeDesejada) {
                throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNome());
            }
        }

        return repo.saveAll(itens);
    }

    public void deletar(Integer id) {
        repo.deleteById(id);
    }

}
