package br.uema.loja_Roupas.dto;

import br.uema.loja_Roupas.entity.Cliente;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.List;

@Getter
@Setter
public class ResumoPedidoDTO {
    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;
    private String status;
    private Cliente cliente;
    private List<ItemDetalhadoDTO> detalhes;
    private BigDecimal precoTotal;
}
