package br.senai.sc.engajamento.reacoes.controller;

import br.senai.sc.engajamento.reacoes.model.command.reacao.BuscarTodosPorVideoReacaoCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacao.BuscarUmReacaoCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacao.CriarReacaoCommand;
import br.senai.sc.engajamento.reacoes.model.entity.Reacao;
import br.senai.sc.engajamento.reacoes.service.ReacaoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/engajamento/reacao")
public class ReacaoController {
    private final ReacaoService service;

    @PostMapping
    public ResponseEntity<Void> criar(
            @RequestBody CriarReacaoCommand cmd,
            @RequestHeader("usuarioId") String idUsuario) {
        service.criar(cmd.from(idUsuario));
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Reacao> buscarUm(
            @RequestBody BuscarUmReacaoCommand cmd,
            @RequestHeader("usuarioId") String idUsuario) {
        return ResponseEntity.ok(service.buscarUm(cmd.from(idUsuario)));
    }

    @GetMapping("/buscar-todos-por-video")
    public ResponseEntity<List<Reacao>> buscarTodosPorVideo(@RequestBody BuscarTodosPorVideoReacaoCommand cmd) {
        return ResponseEntity.ok(service.buscarTodosPorVideo(cmd));
    }
}
