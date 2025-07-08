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

    public List<ItensPedido> listarTodos() {
        return repo.findAll();
    }

    public Optional<ItensPedido> buscarPorId(Integer id) {
        return repo.findById(id);
    }

    public ItensPedido salvar(ItensPedido item) {
        Produto produto = item.getProduto();
        int quantidadeDesejada = item.getQuantidade();

        Estoque estoque = estoqueRepository.findByProduto(produto);

        if (estoque == null || estoque.getQuantidade() < quantidadeDesejada) {
            throw new RuntimeException("Estoque insuficiente para o produto: " + produto.getNome());
        }

        return repo.save(item);
    }

    public void deletar(Integer id) {
        repo.deleteById(id);
    }

}

