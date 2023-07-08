package br.senai.sc.engajamento.reacoes.controller;

import br.senai.sc.engajamento.reacoes.model.command.reacao.BuscarUmReacaoCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacao.CriarReacaoCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacao.DeletarUmReacaoCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacao.EditarReacaoCommand;
import br.senai.sc.engajamento.reacoes.model.entity.Reacao;
import br.senai.sc.engajamento.reacoes.service.ReacaoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/api/engajamento/reacao")
public class ReacaoController {
    private final ReacaoService service;

    @PostMapping
    public ResponseEntity<Reacao> criar(@RequestBody @Valid CriarReacaoCommand cmd) {
        return ResponseEntity.ok(service.criar(cmd));
    }

    @GetMapping
    public ResponseEntity<Reacao> buscarUm(@RequestBody @Valid BuscarUmReacaoCommand cmd) {
        return ResponseEntity.ok(service.buscarUm(cmd));
    }

    @GetMapping
    public ResponseEntity<List<Reacao>> buscarTodos() {
        return ResponseEntity.ok(service.buscarTodos());
    }

    @PutMapping
    public ResponseEntity<Reacao> editar(@RequestBody @Valid EditarReacaoCommand cmd) {
        return ResponseEntity.ok(service.editar(cmd));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletar(@RequestBody @Valid DeletarUmReacaoCommand cmd) {
        service.deletar(cmd);
        return ResponseEntity.ok().build();
    }
}
