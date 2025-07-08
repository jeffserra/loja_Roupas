package br.uema.loja_Roupas.service;

import br.uema.loja_Roupas.entity.*;
import br.uema.loja_Roupas.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromocaoService {

    @Autowired private PromocaoRepository repo;

    public List<Promocao> listarTodos() {
        return repo.findAll();
    }

    public Optional<Promocao> buscarPorId(Integer id) {
        return repo.findById(id);
    }

    public Promocao salvar(Promocao obj) {
        return repo.save(obj);
    }

    public Promocao atualizar(Integer id, Promocao novaPromocao) {
        Promocao promocao = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Promoção não encontrada com ID: " + id));

        promocao.setNome(novaPromocao.getNome());
        promocao.setDesconto(novaPromocao.getDesconto());
        promocao.setData_inicio(novaPromocao.getData_inicio());
        promocao.setData_fim(novaPromocao.getData_fim());

        return repo.save(promocao);
    }

    public void deletar(Integer id) {
        repo.deleteById(id);
    }

}

