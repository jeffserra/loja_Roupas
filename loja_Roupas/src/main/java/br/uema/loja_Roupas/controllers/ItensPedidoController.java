package br.uema.loja_Roupas.controllers;

import br.uema.loja_Roupas.entity.ItensPedido;
import br.uema.loja_Roupas.service.ItensPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/itens-pedido")
public class ItensPedidoController {

    @Autowired private ItensPedidoService service;

    @GetMapping
    public ResponseEntity<List<ItensPedido>> listarTodos() {
        List<ItensPedido> lista = service.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItensPedido> buscarPorId(@PathVariable Integer id) {
        Optional<ItensPedido> item = service.buscarPorId(id);
        return item.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // salva um único item
    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody ItensPedido item) {
        try {
            ItensPedido novo = service.salvar(item);
            return ResponseEntity.ok(novo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("erro", e.getMessage())
            );
        }
    }

    // salva uma lista de itens
    @PostMapping("/lista-itens")
    public ResponseEntity<List<ItensPedido>> adicionarVariosItens(@RequestBody List<ItensPedido> itens) {
        List<ItensPedido> itensSalvos = service.salvarTodos(itens);
        return ResponseEntity.ok(itensSalvos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
