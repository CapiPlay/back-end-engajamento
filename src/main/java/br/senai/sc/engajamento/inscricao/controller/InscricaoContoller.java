package br.senai.sc.engajamento.inscricao.controller;

import br.senai.sc.engajamento.inscricao.model.command.BuscarUmInscricaoCommand;
import br.senai.sc.engajamento.inscricao.model.command.CriarInscricaoCommand;
import br.senai.sc.engajamento.inscricao.model.command.DeletarUmInscricaoCommand;
import br.senai.sc.engajamento.inscricao.model.entity.Inscricao;
import br.senai.sc.engajamento.inscricao.service.InscricaoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/api/engajamento/inscricao")
public class InscricaoContoller {
    private final InscricaoService service;

    public ResponseEntity<Inscricao> criar(CriarInscricaoCommand cmd) {
        Inscricao inscricao = new Inscricao();
        BeanUtils.copyProperties(cmd, inscricao);
        return ResponseEntity.ok(service.criar(cmd));
    }

    public ResponseEntity<Inscricao> buscarUm(BuscarUmInscricaoCommand cmd) {
        return ResponseEntity.ok(service.buscarUm(cmd));
    }

    public ResponseEntity<List<Inscricao>> buscarTodos() {
        return ResponseEntity.ok(service.buscarTodos());
    }

    public ResponseEntity<Void> deletar(DeletarUmInscricaoCommand cmd) {
        service.deletar(cmd);
        return ResponseEntity.ok().build();
    }
}
