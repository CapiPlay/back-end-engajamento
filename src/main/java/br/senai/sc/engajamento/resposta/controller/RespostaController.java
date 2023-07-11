package br.senai.sc.engajamento.resposta.controller;

import br.senai.sc.engajamento.resposta.model.command.BuscarTodosPorComentarioRespostaCommand;
import br.senai.sc.engajamento.resposta.model.command.BuscarUmaRespostaCommand;
import br.senai.sc.engajamento.resposta.model.command.CriarRespostaCommand;
import br.senai.sc.engajamento.resposta.model.command.DeletarRespostaCommand;
import br.senai.sc.engajamento.resposta.model.entity.Resposta;
import br.senai.sc.engajamento.resposta.service.RespostaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/resposta")
public class RespostaController {

    private RespostaService respostaService;

    @PostMapping()
    private ResponseEntity<Resposta> criar(
            @RequestBody @Valid CriarRespostaCommand criarRespostaCommand
    ) {
        return ResponseEntity.ok(respostaService.criar(criarRespostaCommand));
    }

    @GetMapping()
    private ResponseEntity<Resposta> buscarUm(
            @RequestBody @Valid BuscarUmaRespostaCommand buscarUmaRespostaCommand
    ) {
        return ResponseEntity.ok(respostaService.buscarUm(buscarUmaRespostaCommand));
    }

    /*Pega todos os comentários referentes a um vídeo*/
    @GetMapping("/buscar-todos-por-comentario")
    private ResponseEntity<List<Resposta>> buscarTodosPorComentario(
            @RequestBody @Valid BuscarTodosPorComentarioRespostaCommand buscarTodosPorComentarioCommand
    ) {
        return ResponseEntity.ok(respostaService.buscarTodosPorComentario(buscarTodosPorComentarioCommand));
    }

//    @PutMapping()
//    private ResponseEntity<Resposta> editar(
//            @RequestBody @Valid EditarRespostaCommand editarRespostaCommand
//    ){
//        return ResponseEntity.ok(respostaService.editar(editarRespostaCommand));
//    }


    @DeleteMapping()
    private ResponseEntity<Void> deletar(
            @RequestBody DeletarRespostaCommand deletarRespostaCommand
    ) {
        respostaService.deletar(deletarRespostaCommand);
        return ResponseEntity.ok().build();
    }

}
