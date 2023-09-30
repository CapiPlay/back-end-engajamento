package br.senai.sc.engajamento.inscricao.controller;

import br.senai.sc.engajamento.inscricao.model.command.BuscarUmInscricaoCommand;
import br.senai.sc.engajamento.inscricao.model.command.CriarInscricaoCommand;
import br.senai.sc.engajamento.inscricao.model.entity.Inscricao;
import br.senai.sc.engajamento.inscricao.service.InscricaoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/engajamento/inscricao")
public class InscricaoController {
    private final InscricaoService service;

    @PostMapping
    public ResponseEntity<Void> criar(
            @RequestHeader("usuarioId") String idUsuario,
            @RequestBody CriarInscricaoCommand cmd) {
        service.criar(cmd.from(idUsuario));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{idCanal}")
    public ResponseEntity<Inscricao> buscarUm(
            @RequestHeader("usuarioId") String idUsuario,
            @PathVariable String idCanal) {
        return ResponseEntity.ok(service.buscarUm(new BuscarUmInscricaoCommand(idUsuario, idCanal)));
    }
}
