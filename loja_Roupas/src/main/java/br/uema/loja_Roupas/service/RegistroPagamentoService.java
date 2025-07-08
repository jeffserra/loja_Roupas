package br.uema.loja_Roupas.service;

import br.uema.loja_Roupas.entity.*;
import br.uema.loja_Roupas.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RegistroPagamentoService {

    @Autowired private RegistroPagamentoRepository repo;

    public List<RegistroPagamento> listarTodos() {
        return repo.findAll();
    }

    public Optional<RegistroPagamento> buscarPorId(Integer id) {
        return repo.findById(id);
    }

    public RegistroPagamento salvar(RegistroPagamento obj) {
        return repo.save(obj);
    }

    public void deletar(Integer id) {
        repo.deleteById(id);
    }

}
