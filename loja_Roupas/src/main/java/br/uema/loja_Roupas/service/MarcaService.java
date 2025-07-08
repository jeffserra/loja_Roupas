package br.uema.loja_Roupas.service;

import br.uema.loja_Roupas.entity.*;
import br.uema.loja_Roupas.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {

    @Autowired private MarcaRepository repo;

    public List<Marca> listarTodos() {
        return repo.findAll();
    }

    public Optional<Marca> buscarPorId(Integer id) {
        return repo.findById(id);
    }

    public Marca salvar(Marca obj) {
        return repo.save(obj);
    }

    public Marca atualizar(Integer id, Marca novaMarca) {
        return repo.findById(id).map(marca -> {
            marca.setNome(novaMarca.getNome());
            return repo.save(marca);
        }).orElseThrow(() -> new RuntimeException("Marca não encontrada"));
    }

    public void deletar(Integer id) {
        repo.deleteById(id);
    }

}

