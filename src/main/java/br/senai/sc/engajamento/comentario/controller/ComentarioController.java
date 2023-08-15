package br.senai.sc.engajamento.comentario.controller;

import br.senai.sc.engajamento.comentario.model.command.*;
import br.senai.sc.engajamento.comentario.model.entity.Comentario;
import br.senai.sc.engajamento.comentario.service.ComentarioService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@CrossOrigin
@AllArgsConstructor
@RequestMapping("/api/engajamento/comentario")
public class ComentarioController {

    private ComentarioService comentarioService;

    @PostMapping
    private ResponseEntity<Comentario> criar(
            @RequestHeader String idUsuario,
            @RequestBody CriarComentarioCommand cmd
    ) {
       return ResponseEntity.ok(comentarioService.criar(cmd.from(idUsuario)));
    }

    @GetMapping
    private ResponseEntity<Comentario> buscarUm(@RequestBody BuscarUmComentarioCommand cmd) {
        Comentario comentario = comentarioService.buscarUm(cmd);
        return ResponseEntity.ok().build();
    }

    /*Pega todos os comentários referentes a um vídeo*/
    @GetMapping("/buscar-todos-por-video/{page}")
    private ResponseEntity<Page<Comentario>> buscarTodosPorVideo(
            @RequestBody BuscarTodosPorVideoComentarioCommand cmd,
            @PathVariable int page
    ) {
        return ResponseEntity.ok().body(comentarioService.buscarTodosPorVideo(cmd, page));
    }

    /*Busca todos os comentários de um vídeo com base na data*/
    @GetMapping("/buscar-todos-por-data/{page}")
    private ResponseEntity<Page<Comentario>> buscarTodosPorData(
            @RequestBody BuscarTodosPorDataComentarioCommand cmd,
            @PathVariable int page
    ) {
       return ResponseEntity.ok(comentarioService.buscarTodosPorData(cmd, page));
    }

    /*Buscar quantidade de respostas de um comentário*/
    @GetMapping("/buscar-quantidade-respostas")
    private ResponseEntity<Integer> buscarQuantidadeRespostas(
            @RequestBody BuscarQuantidadeRepostasComentarioCommand cmd
    ) {
        return ResponseEntity.ok(comentarioService.buscarQuantidadeRespostas(cmd));
    }

    @DeleteMapping
    private ResponseEntity<Void> deletar(
            @RequestHeader String idUsuario,
            @RequestBody DeletarComentarioCommand cmd
    ) {
        comentarioService.deletar(cmd.from(idUsuario));
        return ResponseEntity.ok().build();
    }

}
