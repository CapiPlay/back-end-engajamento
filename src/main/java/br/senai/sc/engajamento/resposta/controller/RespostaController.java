package br.senai.sc.engajamento.resposta.controller;

import br.senai.sc.engajamento.resposta.model.command.BuscarTodosPorComentarioRespostaCommand;
import br.senai.sc.engajamento.resposta.model.command.BuscarUmaRespostaCommand;
import br.senai.sc.engajamento.resposta.model.command.CriarRespostaCommand;
import br.senai.sc.engajamento.resposta.model.command.DeletarRespostaCommand;
import br.senai.sc.engajamento.resposta.model.entity.Resposta;
import br.senai.sc.engajamento.resposta.service.RespostaService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/engajamento/resposta")
public class RespostaController {
    private final RespostaService respostaService;

    @PostMapping
    private ResponseEntity<Resposta> criar(
            @RequestHeader("usuarioId") String idUsuario,
            @RequestBody CriarRespostaCommand cmd) {
        return ResponseEntity.ok(respostaService.criar(cmd.from(idUsuario)));
    }

    @GetMapping
    private ResponseEntity<Resposta> buscarUm(@RequestBody BuscarUmaRespostaCommand cmd) {
        return ResponseEntity.ok(respostaService.buscarUm(cmd));
    }

    /*Pega todos os comentários referentes a um vídeo*/
    @GetMapping("/buscar-todos-por-comentario/{idComentario}")
    private ResponseEntity<Page<Resposta>> buscarTodosPorComentario(
        @PathVariable String idComentario,
        @RequestParam("page") int page
    ) {
        BuscarTodosPorComentarioRespostaCommand cmd = new BuscarTodosPorComentarioRespostaCommand();
        return ResponseEntity.ok(respostaService.buscarTodosPorComentario(cmd.from(idComentario), page));
    }

    @DeleteMapping
    private ResponseEntity<Void> deletar(
            @RequestHeader("usuarioId") String idUsuario,
            @RequestBody DeletarRespostaCommand cmd) {
        respostaService.deletar(cmd.from(idUsuario));
        return ResponseEntity.ok().build();
    }

}
