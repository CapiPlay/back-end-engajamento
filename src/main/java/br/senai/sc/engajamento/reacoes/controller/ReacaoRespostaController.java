package br.senai.sc.engajamento.reacoes.controller;

import br.senai.sc.engajamento.reacoes.model.command.reacaoResposta.BuscarTodosPorComentarioReacaoRespostaCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacaoResposta.BuscarUmReacaoRespostaCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacaoResposta.CriarReacaoRespostaCommand;
import br.senai.sc.engajamento.reacoes.model.entity.ReacaoRespota;
import br.senai.sc.engajamento.reacoes.service.ReacaoRespostaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/engajamento/reacaoResposta")
public class ReacaoRespostaController {
    private ReacaoRespostaService service;

    @PostMapping
    public ResponseEntity<Void> criar(
            @RequestBody CriarReacaoRespostaCommand cmd,
            @RequestHeader String idUsuario) {
        service.criar(cmd.from(idUsuario));
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<ReacaoRespota> buscarUm(
            @RequestBody BuscarUmReacaoRespostaCommand cmd,
            @RequestHeader String idUsuario) {
        return ResponseEntity.ok(service.buscarUm(cmd.from(idUsuario)));
    }

    @GetMapping("/buscar-todos-por-resposta")
    public ResponseEntity<List<ReacaoRespota>> buscarTodos(
            @RequestBody BuscarTodosPorComentarioReacaoRespostaCommand cmd) {
        return ResponseEntity.ok(service.buscarTodos(cmd));
    }
}
