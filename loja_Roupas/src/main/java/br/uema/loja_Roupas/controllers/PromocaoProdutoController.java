package br.uema.loja_Roupas.controllers;

import br.uema.loja_Roupas.entity.Promocao;
import br.uema.loja_Roupas.entity.PromocaoProduto;
import br.uema.loja_Roupas.entity.PromocaoProdutoId;
import br.uema.loja_Roupas.service.PromocaoProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/promocoes-produtos")
public class PromocaoProdutoController {

    @Autowired private PromocaoProdutoService service;

    @GetMapping
    public ResponseEntity<List<PromocaoProduto>> listarTodos() {
        List<PromocaoProduto> lista = service.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{idPromocao}/{idProduto}")
    public ResponseEntity<PromocaoProduto> buscarPorId(
            @PathVariable Integer idPromocao,
            @PathVariable Integer idProduto) {

        PromocaoProdutoId id = PromocaoProdutoId.builder()
                .idPromocao(idPromocao)
                .idProduto(idProduto)
                .build();

        Optional<PromocaoProduto> promocaoProduto = service.buscarPorId(id);
        return promocaoProduto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody PromocaoProduto obj) {
        try {
            PromocaoProduto novo = service.salvar(obj);
            return ResponseEntity.ok(novo);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{idPromocao}/{idProduto}")
    public ResponseEntity<Void> deletar(
            @PathVariable Integer idPromocao,
            @PathVariable Integer idProduto) {

        PromocaoProdutoId id = PromocaoProdutoId.builder()
                .idPromocao(idPromocao)
                .idProduto(idProduto)
                .build();

        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
