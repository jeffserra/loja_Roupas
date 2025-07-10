package br.uema.loja_Roupas.service;

import br.uema.loja_Roupas.entity.ItensPedido;
import br.uema.loja_Roupas.entity.ItensPedidoPromocao;
import br.uema.loja_Roupas.repository.ItensPedidoPromocaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItensPedidoPromocaoService {

    @Autowired
    private ItensPedidoPromocaoRepository repository;

    public Optional<ItensPedidoPromocao> buscarPorItem(ItensPedido item) {
        return repository.findByItem(item);
    }
}
