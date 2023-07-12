package br.senai.sc.engajamento.historico.controller;


import br.senai.sc.engajamento.historico.model.commands.BuscarUmHistoricoCommand;
import br.senai.sc.engajamento.historico.model.commands.CriarHistoricoCommand;
import br.senai.sc.engajamento.historico.model.entity.Historico;
import br.senai.sc.engajamento.historico.service.HistoricoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/engajamento/historico")
public class HistoricoController {

    private final HistoricoService historicoService;

    @PostMapping()
    private ResponseEntity<Void> criar(
            @RequestBody @Valid CriarHistoricoCommand criarHistoricoCommand
    ) {
        historicoService.criar(criarHistoricoCommand);
        return ResponseEntity.ok().build();
    }

    @GetMapping()
    private ResponseEntity<Historico> buscarUm(
            @RequestBody @Valid BuscarUmHistoricoCommand buscarUmHistoricoCommand
    ) {
        return ResponseEntity.ok(historicoService.buscarUm(buscarUmHistoricoCommand));
    }

//    Buscar histórico só pelo user ou vídeo

}
