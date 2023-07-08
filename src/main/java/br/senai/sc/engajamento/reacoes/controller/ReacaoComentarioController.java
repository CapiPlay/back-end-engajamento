package br.senai.sc.engajamento.reacoes.controller;

import br.senai.sc.engajamento.reacoes.model.command.reacaoComentario.AlternarReacaoComentarioCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacaoComentario.BuscarUmReacaoComentarioCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacaoComentario.DeletarUmReacaoComentarioCommand;
import br.senai.sc.engajamento.reacoes.model.entity.ReacaoComentario;
import br.senai.sc.engajamento.reacoes.service.ReacaoComentarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/api/engajamento/reacaoComentario")
public class ReacaoComentarioController {
    private final ReacaoComentarioService service;

    @PostMapping
    public ResponseEntity<ReacaoComentario> criar(@RequestBody @Valid AlternarReacaoComentarioCommand cmd) {
        return ResponseEntity.ok(service.criar(cmd));
    }

    @GetMapping
    public ResponseEntity<ReacaoComentario> buscarUm(@RequestBody @Valid BuscarUmReacaoComentarioCommand cmd) {
        return ResponseEntity.ok(service.buscarUm(cmd));
    }

    @GetMapping
    public ResponseEntity<List<ReacaoComentario>> buscarTodos() {
        return ResponseEntity.ok(service.buscarTodos());
    }

    @PutMapping
    public ResponseEntity<ReacaoComentario> alternar(@RequestBody @Valid AlternarReacaoComentarioCommand cmd) {
        return ResponseEntity.ok(service.alternar(cmd));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletar(@RequestBody @Valid DeletarUmReacaoComentarioCommand cmd) {
        service.deletar(cmd);
        return ResponseEntity.ok().build();
    }
}
