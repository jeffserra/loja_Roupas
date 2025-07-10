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
@RequestMapping("/promocoes")
public class PromocaoController {

    @Autowired private PromocaoService service;

    @GetMapping
    public ResponseEntity<List<Promocao>> listarTodos() {
        List<Promocao> lista = service.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Promocao> buscarPorId(@PathVariable Integer id) {
        Optional<Promocao> promocao = service.buscarPorId(id);
        return promocao.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Promocao promocao) {
        try {
            Promocao novo = service.salvar(promocao);
            return ResponseEntity.ok(novo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("erro", e.getMessage())
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Promocao> atualizar(@PathVariable Integer id, @RequestBody Promocao novaPromocao) {
        try {
            Promocao atualizado = service.atualizar(id, novaPromocao);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
