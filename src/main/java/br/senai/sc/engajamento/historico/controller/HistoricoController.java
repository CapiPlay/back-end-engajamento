package br.senai.sc.engajamento.historico.controller;

import br.senai.sc.engajamento.historico.model.commands.BuscarTodosPorDataHistoricoCommand;
import br.senai.sc.engajamento.historico.model.commands.BuscarUmHistoricoCommand;
import br.senai.sc.engajamento.historico.model.commands.CriarHistoricoCommand;
import br.senai.sc.engajamento.historico.model.entity.Historico;
import br.senai.sc.engajamento.historico.service.HistoricoService;
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
    public ResponseEntity<Void> criar(
            @RequestBody CriarHistoricoCommand cmd,
            @RequestHeader("usuarioId") String idUsuario) {
        historicoService.criar(cmd.from(idUsuario));
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    public ResponseEntity<Historico> buscarUm(
            @RequestBody BuscarUmHistoricoCommand cmd,
            @RequestHeader("usuarioId") String idUsuario) {
        return ResponseEntity.ok(historicoService.buscarUm(cmd.from(idUsuario)));
    }

    /*Buscar históricos de um usuário por data*/
    @GetMapping("/buscar-todos-historicos-por-data")
    public ResponseEntity<List<Historico>> buscarTodosHistoricosPorData(
            @RequestBody BuscarTodosPorDataHistoricoCommand cmd,
            @RequestHeader("usuarioId") String idUsuario
    ) {
        return ResponseEntity.ok(historicoService.buscarTodosPorData(cmd.from(idUsuario)));
    }

    /*Buscar históricos de um usuário*/
    @GetMapping("/buscar-todos-historicos-por-usuario")
    public ResponseEntity<List<Historico>> buscarTodosHistoricosPorUsuario(@RequestHeader("usuarioId") String idUsuario) {
        return ResponseEntity.ok(historicoService.buscarTodosPorUsuario(idUsuario));
    }
}
