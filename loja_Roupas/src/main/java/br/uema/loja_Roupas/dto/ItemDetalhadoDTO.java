package br.uema.loja_Roupas.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class ItemDetalhadoDTO {
    private String nomeProduto;
    private int quantidade;
    private BigDecimal precoOriginal;
    private BigDecimal descontoPercentual;
    private BigDecimal precoComDesconto;
    private BigDecimal totalPorItem;
}
