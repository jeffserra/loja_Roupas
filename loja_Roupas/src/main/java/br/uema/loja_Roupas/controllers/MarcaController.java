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
@RequestMapping("/marcas")
public class MarcaController {

    @Autowired private MarcaService service;

    @GetMapping
    public ResponseEntity<List<Marca>> listarTodos() {
        List<Marca> lista = service.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marca> buscarPorId(@PathVariable Integer id) {
        Optional<Marca> marca = service.buscarPorId(id);
        return marca.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Marca marca) {
        try {
            Marca novo = service.salvar(marca);
            return ResponseEntity.ok(novo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("erro", e.getMessage())
            );
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Marca> atualizar(@PathVariable Integer id, @RequestBody Marca obj) {
        try {
            Marca atualizado = service.atualizar(id, obj);
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

