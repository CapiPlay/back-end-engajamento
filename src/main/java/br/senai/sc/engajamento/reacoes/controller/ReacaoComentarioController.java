package br.senai.sc.engajamento.reacoes.controller;

import br.senai.sc.engajamento.reacoes.model.command.reacaoComentario.BuscarTodosPorComentarioReacaoComentarioCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacaoComentario.BuscarUmReacaoComentarioCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacaoComentario.CriarReacaoComentarioCommand;
import br.senai.sc.engajamento.reacoes.model.entity.ReacaoComentario;
import br.senai.sc.engajamento.reacoes.service.ReacaoComentarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/engajamento/reacaoComentario")
public class ReacaoComentarioController {
    private final ReacaoComentarioService service;

    @PostMapping
    public ResponseEntity<Void> criar(
            @RequestBody CriarReacaoComentarioCommand cmd,
            @RequestHeader("usuarioId") String idUsuario
    ) {
        service.criar(cmd.from(idUsuario));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{idComentario}")
    public ResponseEntity<ReacaoComentario> buscarUm(
            @PathVariable String idComentario,
            @RequestHeader("usuarioId") String idUsuario) {
        BuscarUmReacaoComentarioCommand b = new BuscarUmReacaoComentarioCommand(idUsuario, idComentario);
        return ResponseEntity.ok(service.buscarUm(b));
    }

    @GetMapping("/buscar-todos-por-comentario")
    public ResponseEntity<List<ReacaoComentario>> buscarTodosPorComentario(
            @RequestBody BuscarTodosPorComentarioReacaoComentarioCommand cmd) {
        return ResponseEntity.ok(service.buscarTodosPorComentario(cmd));
    }
}
