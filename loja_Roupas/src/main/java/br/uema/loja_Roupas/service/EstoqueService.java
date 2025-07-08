package br.uema.loja_Roupas.service;

import br.uema.loja_Roupas.entity.*;
import br.uema.loja_Roupas.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstoqueService {

    @Autowired private EstoqueRepository repo;

    public List<Estoque> listarTodos() {
        return repo.findAll();
    }

    public Optional<Estoque> buscarPorId(Integer id) {
        return repo.findById(id);
    }

    public Estoque salvar(Estoque obj) {
        return repo.save(obj);
    }

    public Estoque atualizar(Integer id, Estoque novoEstoque) {
        return repo.findById(id).map(estoque -> {
            estoque.setQuantidade(novoEstoque.getQuantidade());
            return repo.save(estoque);
        }).orElseThrow(() -> new RuntimeException("Estoque não encontrado"));
    }

    public void deletar(Integer id) {
        repo.deleteById(id);
    }

}

