package br.uema.loja_Roupas.service;

import br.uema.loja_Roupas.entity.*;
import br.uema.loja_Roupas.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

@Service
public class RegistroPagamentoService {

    @Autowired private RegistroPagamentoRepository repo;
    @Autowired private PedidoRepository pedidoRepository;
    @Autowired private ItensPedidoRepository itensPedidoRepository;
    @Autowired private ItensPedidoPromocaoRepository itensPedidoPromocaoRepository;

    public List<RegistroPagamento> listarTodos() {
        return repo.findAll();
    }

    public Optional<RegistroPagamento> buscarPorId(Integer id) {
        return repo.findById(id);
    }

    public RegistroPagamento salvar(RegistroPagamento obj) {
        Pedido pedido = obj.getPedido();

        if (pedido == null || pedido.getId() == null) {
            throw new RuntimeException("Pedido não informado ou inválido.");
        }

        pedido = pedidoRepository.findById(pedido.getId())
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado."));

        List<ItensPedido> itens = itensPedidoRepository.findByPedido(pedido);
        BigDecimal totalCalculado = BigDecimal.ZERO;

        for (ItensPedido item : itens) {
            Produto produto = item.getProduto();
            BigDecimal precoOriginal = BigDecimal.valueOf(produto.getPreco());

            // Verifica se há promoção associada a este item específico
            Optional<ItensPedidoPromocao> itemPromo = itensPedidoPromocaoRepository.findByItem(item);
            BigDecimal desconto = itemPromo.map(promo -> promo.getPromocao().getDesconto()).orElse(BigDecimal.ZERO);

            BigDecimal precoComDesconto = precoOriginal
                    .multiply(BigDecimal.ONE.subtract(desconto.divide(BigDecimal.valueOf(100))))
                    .setScale(2, RoundingMode.HALF_UP);

            BigDecimal subtotal = precoComDesconto
                    .multiply(BigDecimal.valueOf(item.getQuantidade()))
                    .setScale(2, RoundingMode.HALF_UP);

            totalCalculado = totalCalculado.add(subtotal);
        }

        BigDecimal valorPago = obj.getValor();

        if (valorPago.setScale(2, RoundingMode.HALF_UP)
                .compareTo(totalCalculado.setScale(2, RoundingMode.HALF_UP)) != 0) {
            throw new RuntimeException("Valor do pagamento (R$ " + valorPago +
                    ") não corresponde ao total do pedido com desconto (R$ " + totalCalculado + ").");
        }

        pedido.setStatus("Concluído");
        pedidoRepository.save(pedido);

        return repo.save(obj);
    }

    public void deletar(Integer id) {
        repo.deleteById(id);
    }
}
