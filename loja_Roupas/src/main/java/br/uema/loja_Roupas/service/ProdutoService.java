package br.uema.loja_Roupas.service;

import br.uema.loja_Roupas.entity.*;
import br.uema.loja_Roupas.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired private ProdutoRepository repo;

    public List<Produto> listarTodos() {
        return repo.findAll();
    }

    public Optional<Produto> buscarPorId(Integer id) {
        return repo.findById(id);
    }

    public Produto salvar(Produto obj) {
        return repo.save(obj);
    }

    public Produto atualizar(Integer id, Produto novoProduto) {
        Produto produto = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com ID: " + id));

        produto.setNome(novoProduto.getNome());
        produto.setDescricao(novoProduto.getDescricao());
        produto.setPreco(novoProduto.getPreco());
        produto.setCategoria(novoProduto.getCategoria());
        produto.setTamanho(novoProduto.getTamanho());
        produto.setCor(novoProduto.getCor());
        produto.setGenero(novoProduto.getGenero());
        produto.setMarca(novoProduto.getMarca());

        return repo.save(produto);
    }

    public void deletar(Integer id) {
        repo.deleteById(id);
    }

}
