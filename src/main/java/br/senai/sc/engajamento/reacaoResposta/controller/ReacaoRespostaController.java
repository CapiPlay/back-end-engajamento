package br.senai.sc.engajamento.reacaoResposta.controller;

import br.senai.sc.engajamento.reacaoResposta.model.command.BuscarUmReacaoRespostaCommand;
import br.senai.sc.engajamento.reacaoResposta.model.command.CriarReacaoRespostaCommand;
import br.senai.sc.engajamento.reacaoResposta.model.command.DeletarUmReacaoRespostaCommand;
import br.senai.sc.engajamento.reacaoResposta.model.command.EditarReacaoRespostaCommand;
import br.senai.sc.engajamento.reacaoResposta.model.entity.ReacaoRespota;
import br.senai.sc.engajamento.reacaoResposta.service.ReacaoRespostaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/api/engajamento/reacaoResposta")
public class ReacaoRespostaController {
    private ReacaoRespostaService service;

    @PostMapping
    public ResponseEntity<ReacaoRespota> criar(@RequestBody @Valid CriarReacaoRespostaCommand cmd) {
        return ResponseEntity.ok(service.criar(cmd));
    }

    @GetMapping
    public ResponseEntity<ReacaoRespota> buscarUm(@RequestBody @Valid BuscarUmReacaoRespostaCommand cmd) {
        return ResponseEntity.ok(service.buscarUm(cmd));
    }

    @GetMapping
    public ResponseEntity<List<ReacaoRespota>> buscarTodos() {
        return ResponseEntity.ok(service.buscarTodos());
    }

    @PutMapping
    public ResponseEntity<ReacaoRespota> editar(@RequestBody @Valid EditarReacaoRespostaCommand cmd) {
        return ResponseEntity.ok(service.editar(cmd));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletar(@RequestBody @Valid DeletarUmReacaoRespostaCommand cmd) {
        service.deletar(cmd);
        return ResponseEntity.ok().build();
    }
}
