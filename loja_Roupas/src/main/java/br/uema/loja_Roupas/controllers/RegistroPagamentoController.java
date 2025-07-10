package br.uema.loja_Roupas.controllers;

import br.uema.loja_Roupas.entity.*;
import br.uema.loja_Roupas.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/registro-pagamento")
public class RegistroPagamentoController {

    @Autowired private RegistroPagamentoService service;

    @GetMapping
    public ResponseEntity<List<RegistroPagamento>> listarTodos() {
        List<RegistroPagamento> lista = service.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroPagamento> buscarPorId(@PathVariable Integer id) {
        Optional<RegistroPagamento> registroPagamento = service.buscarPorId(id);
        return registroPagamento.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody RegistroPagamento reg) {
        try {
            RegistroPagamento novo = service.salvar(reg);
            return ResponseEntity.ok(novo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("erro", e.getMessage())
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
