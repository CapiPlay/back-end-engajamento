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

    @GetMapping("/{idVideo}")
    public ResponseEntity<Reacao> buscarUm(
            @PathVariable String idVideo,
            @RequestHeader("usuarioId") String idUsuario) {
        BuscarUmReacaoCommand b = new BuscarUmReacaoCommand(idUsuario, idVideo);
        return ResponseEntity.ok(service.buscarUm(b));
    }

    @GetMapping("/buscar-todos-por-video")
    public ResponseEntity<List<Reacao>> buscarTodosPorVideo(@ModelAttribute BuscarTodosPorVideoReacaoCommand cmd) {
        return ResponseEntity.ok(service.buscarTodosPorVideo(cmd));
    }
}
