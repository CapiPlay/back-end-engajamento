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
    private ResponseEntity<Comentario> criar(
            @RequestBody @Valid CriarComentarioCommand criarComentarioCommand
    ) {
        return ResponseEntity.ok(comentarioService.criar(criarComentarioCommand));
    }

    @GetMapping
    private ResponseEntity<Comentario> buscarUm(
            @RequestBody @Valid BuscarUmComentarioCommand buscarUmComentarioCommand
    ) {
        return ResponseEntity.ok(comentarioService.buscarUm(buscarUmComentarioCommand));
    }

    /*Pega todos os comentários referentes a um vídeo*/
    @GetMapping("/buscar-todos-por-video")
    private ResponseEntity<List<Comentario>> buscarTodosPorVideo(
            @RequestBody @Valid BuscarTodosPorVideoComentarioCommand buscarTodosPorVideoComentarioCommand
    ) {
        return ResponseEntity.ok(comentarioService.buscarTodosPorVideo(buscarTodosPorVideoComentarioCommand));
    }

    /*Busca todos os comentários de um vídeo com base na data*/
    @GetMapping("/buscar-todos-por-data")
    private ResponseEntity<List<Comentario>> buscarTodosPorData(
            @RequestBody @Valid BuscarTodosPorDataComentarioCommand buscarTodosPorDataComentarioCommand
    ) {
        return ResponseEntity.ok(comentarioService.buscarTodosPorData(buscarTodosPorDataComentarioCommand));
    }

    /*Buscar quantidade de respostas de um comentário*/
    @GetMapping("/buscar-quantidade-respostas")
    private ResponseEntity<Integer> buscarQuantidadeRespostas(
            @RequestBody @Valid BuscarQuantidadeRepostasComentarioCommand buscarQuantidadeRepostasComentarioCommand
    ) {
        return ResponseEntity.ok(comentarioService.buscarQuantidadeRespostas(buscarQuantidadeRepostasComentarioCommand));
    }

    @PutMapping("/adicionar-resposta")
    private ResponseEntity<Comentario> adicionarResposta(
            @RequestBody @Valid AdicionarRespostaComentarioCommand adicionarRespostaComentarioCommand
    ) {
        comentarioService.adicionarResposta(adicionarRespostaComentarioCommand);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    private ResponseEntity<Void> deletar(
            @RequestBody @Valid DeletarComentarioCommand deletarComentarioCommand
    ) {
        comentarioService.deletar(deletarComentarioCommand);
        return ResponseEntity.ok().build();
    }

}
