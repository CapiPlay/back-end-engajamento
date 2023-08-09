package br.senai.sc.engajamento.comentario.controller;

import br.senai.sc.engajamento.comentario.model.command.*;
import br.senai.sc.engajamento.comentario.model.entity.Comentario;
import br.senai.sc.engajamento.comentario.service.ComentarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
    private ResponseEntity<Comentario> criar(@RequestHeader String usuarioId,
            @RequestBody @Valid CriarComentarioCommand cmd
    ) {

        return ResponseEntity.ok(comentarioService.criar(cmd.from(usuarioId)));
    }

    @GetMapping
    private ResponseEntity<Comentario> buscarUm(
            @RequestBody @Valid BuscarUmComentarioCommand cmd
    ) {
        return ResponseEntity.ok(comentarioService.buscarUm(cmd));
    }

    /*Pega todos os comentários referentes a um vídeo*/
    @GetMapping("/buscar-todos-por-video")
    private ResponseEntity<List<Comentario>> buscarTodosPorVideo(
            @RequestBody @Valid BuscarTodosPorVideoComentarioCommand cmd
    ) {
        return ResponseEntity.ok(comentarioService.buscarTodosPorVideo(cmd));
    }

    /*Busca todos os comentários de um vídeo com base na data*/
    @GetMapping("/buscar-todos-por-data")
    private ResponseEntity<List<Comentario>> buscarTodosPorData(
            @RequestBody @Valid BuscarTodosPorDataComentarioCommand cmd
    ) {
        return ResponseEntity.ok(comentarioService.buscarTodosPorData(cmd));
    }

    /*Buscar quantidade de respostas de um comentário*/
    @GetMapping("/buscar-quantidade-respostas")
    private ResponseEntity<Integer> buscarQuantidadeRespostas(
            @RequestBody @Valid BuscarQuantidadeRepostasComentarioCommand cmd
    ) {
        return ResponseEntity.ok(comentarioService.buscarQuantidadeRespostas(cmd));
    }

    @PutMapping("/adicionar-resposta")
    private ResponseEntity<Comentario> adicionarResposta(
            @RequestBody @Valid AdicionarRespostaComentarioCommand cmd
    ) {
        comentarioService.adicionarResposta(cmd);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    private ResponseEntity<Void> deletar(
            @RequestBody @Valid DeletarComentarioCommand cmd
    ) {
        comentarioService.deletar(cmd);
        return ResponseEntity.ok().build();
    }

}
