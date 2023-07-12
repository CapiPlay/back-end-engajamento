package br.senai.sc.engajamento.reacoes.controller;

import br.senai.sc.engajamento.exception.NaoEncontradoException;
import br.senai.sc.engajamento.reacoes.model.command.reacao.BuscarUmReacaoCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacao.CriarReacaoCommand;
import br.senai.sc.engajamento.reacoes.model.entity.Reacao;
import br.senai.sc.engajamento.reacoes.service.ReacaoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@Controller
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/engajamento/reacao")
public class ReacaoController {
    private final ReacaoService service;

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody @Valid CriarReacaoCommand cmd) {
        service.criar(cmd);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Reacao> buscarUm(@RequestBody @Valid BuscarUmReacaoCommand cmd) {
        Reacao reacao = null;
        try {
            reacao = service.buscarUm(cmd);
        } catch (NaoEncontradoException e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return ResponseEntity.ok(reacao);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Reacao>> buscarTodos() {
        return ResponseEntity.ok(service.buscarTodos());
    }
}
