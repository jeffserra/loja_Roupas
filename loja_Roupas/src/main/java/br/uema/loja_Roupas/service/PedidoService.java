package br.uema.loja_Roupas.service;

import br.uema.loja_Roupas.dto.ItemDetalhadoDTO;
import br.uema.loja_Roupas.dto.ResumoPedidoDTO;
import br.uema.loja_Roupas.entity.*;
import br.uema.loja_Roupas.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired private PedidoRepository pedidoRepository;
    @Autowired private ItensPedidoRepository itemPedidoRepository;
    @Autowired private ItensPedidoPromocaoRepository itensPedidoPromocaoRepository;

    public List<ResumoPedidoDTO> listarTodos() {
        List<Pedido> pedidos = pedidoRepository.findAll();
        List<ResumoPedidoDTO> listaResumo = new ArrayList<>();

        for (Pedido pedido : pedidos) {
            listaResumo.add(buscarPorId(pedido.getId()));
        }

        return listaResumo;
    }

    public ResumoPedidoDTO buscarPorId(Integer idPedido) {
        Pedido pedido = pedidoRepository.findById(idPedido)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));

        List<ItensPedido> itens = itemPedidoRepository.findByPedido(pedido);
        List<ItemDetalhadoDTO> detalhes = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        for (ItensPedido item : itens) {
            Produto produto = item.getProduto();
            BigDecimal precoOriginal = BigDecimal.valueOf(produto.getPreco()).setScale(2, RoundingMode.HALF_UP);

            BigDecimal descontoPercentual = itensPedidoPromocaoRepository.findByItem(item)
                    .map(promo -> promo.getPromocao().getDesconto().setScale(2, RoundingMode.HALF_UP))
                    .orElse(BigDecimal.ZERO);

            BigDecimal precoComDesconto = precoOriginal
                    .multiply(BigDecimal.ONE.subtract(descontoPercentual.divide(BigDecimal.valueOf(100), 4, RoundingMode.HALF_UP)))
                    .setScale(2, RoundingMode.HALF_UP);

            BigDecimal totalItem = precoComDesconto
                    .multiply(BigDecimal.valueOf(item.getQuantidade()))
                    .setScale(2, RoundingMode.HALF_UP);

            ItemDetalhadoDTO dto = new ItemDetalhadoDTO();
            dto.setNomeProduto(produto.getNome());
            dto.setQuantidade(item.getQuantidade());
            dto.setPrecoOriginal(precoOriginal);
            dto.setDescontoPercentual(descontoPercentual);
            dto.setPrecoComDesconto(precoComDesconto);
            dto.setTotalPorItem(totalItem);

            detalhes.add(dto);
            total = total.add(totalItem);
        }

        ResumoPedidoDTO resumo = new ResumoPedidoDTO();
        resumo.setId(pedido.getId());
        resumo.setData(pedido.getData());
        resumo.setStatus(pedido.getStatus());
        resumo.setCliente(pedido.getCliente());
        resumo.setDetalhes(detalhes);
        resumo.setPrecoTotal(total.setScale(2, RoundingMode.HALF_UP));

        return resumo;
    }

    public Pedido salvar(Pedido pedido) {
        pedido.setStatus("Pendente");
        return pedidoRepository.save(pedido);
    }

    public Pedido atualizar(Integer id, Pedido novoPedido) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado com ID: " + id));

        pedido.setData(novoPedido.getData());
        pedido.setStatus(novoPedido.getStatus());
        pedido.setCliente(novoPedido.getCliente());

        return pedidoRepository.save(pedido);
    }

    public void deletar(Integer id) {
        pedidoRepository.deleteById(id);
    }
}
