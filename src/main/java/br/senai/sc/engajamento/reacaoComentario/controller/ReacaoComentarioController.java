package br.senai.sc.engajamento.reacaoComentario.controller;

import br.senai.sc.engajamento.reacao.model.command.DeletarUmReacaoCommand;
import br.senai.sc.engajamento.reacaoComentario.model.command.BuscarUmReacaoComentarioCommand;
import br.senai.sc.engajamento.reacaoComentario.model.command.CriarReacaoComentarioCommand;
import br.senai.sc.engajamento.reacaoComentario.model.command.EditarReacaoComentarioCommand;
import br.senai.sc.engajamento.reacaoComentario.model.entity.ReacaoComentario;
import br.senai.sc.engajamento.reacaoComentario.service.ReacaoComentarioService;
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
    public ResponseEntity<ReacaoComentario> criar(@RequestBody @Valid CriarReacaoComentarioCommand cmd) {
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
    public ResponseEntity<ReacaoComentario> editar(@RequestBody @Valid EditarReacaoComentarioCommand cmd) {
        return ResponseEntity.ok(service.editar(cmd));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletar(@RequestBody @Valid DeletarUmReacaoCommand cmd) {
        service.deletar(cmd);
        return ResponseEntity.ok().build();
    }
}
