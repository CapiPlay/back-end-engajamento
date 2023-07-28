package br.senai.sc.engajamento.historico.controller;


import br.senai.sc.engajamento.comentario.model.command.BuscarQuantidadeRepostasComentarioCommand;
import br.senai.sc.engajamento.historico.model.commands.BuscarTodosPorDataHistoricoCommand;
import br.senai.sc.engajamento.historico.model.commands.BuscarTodosPorUsuarioHistoricoCommand;
import br.senai.sc.engajamento.historico.model.commands.BuscarUmHistoricoCommand;
import br.senai.sc.engajamento.historico.model.commands.CriarHistoricoCommand;
import br.senai.sc.engajamento.historico.model.entity.Historico;
import br.senai.sc.engajamento.historico.service.HistoricoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/engajamento/historico")
public class HistoricoController {

    private final HistoricoService historicoService;

    @PostMapping()
    private ResponseEntity<Void> criar(
            @RequestBody @Valid CriarHistoricoCommand cmd
    ) {
        historicoService.criar(cmd);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    private ResponseEntity<Historico> buscarUm(
            @RequestBody @Valid BuscarUmHistoricoCommand cmd
    ) {
        return ResponseEntity.ok(historicoService.buscarUm(cmd));
    }

    /*Buscar históricos de um usuário por data*/
    @GetMapping("/buscar-todos-históricos-por-data")
    private ResponseEntity<List<Historico>> buscarTodosHistoricosPorData(
            @RequestBody @Valid BuscarTodosPorDataHistoricoCommand cmd
            ) {
        return ResponseEntity.ok(historicoService.buscarTodosPorData(cmd));
    }

    /*Buscar históricos de um usuário*/
    @GetMapping("/buscar-todos-históricos-por-usuario")
    private ResponseEntity<List<Historico>> buscarTodosHistoricosPorUsuario(
            @RequestBody @Valid BuscarTodosPorUsuarioHistoricoCommand cmd
    ) {
        return ResponseEntity.ok(historicoService.buscarTodosPorUsuario(cmd));
    }
}
