package br.senai.sc.engajamento.reacoes.controller;

import br.senai.sc.engajamento.exception.NaoEncontradoException;
import br.senai.sc.engajamento.reacoes.model.command.reacaoResposta.BuscarUmReacaoRespostaCommand;
import br.senai.sc.engajamento.reacoes.model.command.reacaoResposta.CriarReacaoRespostaCommand;
import br.senai.sc.engajamento.reacoes.model.entity.ReacaoRespota;
import br.senai.sc.engajamento.reacoes.service.ReacaoRespostaService;
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
@RequestMapping("/api/engajamento/reacaoResposta")
public class ReacaoRespostaController {
    private ReacaoRespostaService service;

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody @Valid CriarReacaoRespostaCommand cmd) {
        service.criar(cmd);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<ReacaoRespota> buscarUm(@RequestBody @Valid BuscarUmReacaoRespostaCommand cmd) {
        ReacaoRespota reacaoRespota = null;
        try {
            reacaoRespota = service.buscarUm(cmd);
        } catch (NaoEncontradoException e) {
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return ResponseEntity.ok(reacaoRespota);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<ReacaoRespota>> buscarTodos() {
        return ResponseEntity.ok(service.buscarTodos());
    }
}
