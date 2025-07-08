package br.uema.loja_Roupas.service;

import br.uema.loja_Roupas.entity.*;
import br.uema.loja_Roupas.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromocaoProdutoService {

    @Autowired private PromocaoProdutoRepository repo;

    public List<PromocaoProduto> listarTodos() {
        return repo.findAll();
    }

    public Optional<PromocaoProduto> buscarPorId(PromocaoProdutoId id) {
        return repo.findById(id);
    }

    public PromocaoProduto salvar(PromocaoProduto obj) {
        return repo.save(obj);
    }

    public void deletar(PromocaoProdutoId id) {
        repo.deleteById(id);
    }

}
