package br.senai.sc.engajamento.inscricao.controller;

import br.senai.sc.engajamento.inscricao.model.command.BuscarUmInscricaoCommand;
import br.senai.sc.engajamento.inscricao.model.command.CriarInscricaoCommand;
import br.senai.sc.engajamento.inscricao.model.entity.Inscricao;
import br.senai.sc.engajamento.inscricao.service.InscricaoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/api/engajamento/inscricao")
public class InscricaoContoller {
    private final InscricaoService service;

    @PostMapping
    public ResponseEntity<Void> criar(@RequestBody @Valid CriarInscricaoCommand cmd) {
        Inscricao inscricao = new Inscricao();
        BeanUtils.copyProperties(cmd, inscricao);
        service.criar(cmd);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Inscricao> buscarUm(@RequestBody @Valid BuscarUmInscricaoCommand cmd) {
        return ResponseEntity.ok(service.buscarUm(cmd));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Inscricao>> buscarTodos() {
        return ResponseEntity.ok(service.buscarTodos());
    }
}
