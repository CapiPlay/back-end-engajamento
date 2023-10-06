package br.senai.sc.engajamento.usuario.controller;

import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.usuario.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@AllArgsConstructor
@CrossOrigin
@RequestMapping("/api/engajamento/usuario")
public class UsuarioController {

    private UsuarioService service;

    @GetMapping("/{idUsuario}")
    public ResponseEntity<Usuario> buscar(
            @PathVariable String idUsuario
    ) {
        return ResponseEntity.ok(service.retornaUsuario(idUsuario));
    }

}
