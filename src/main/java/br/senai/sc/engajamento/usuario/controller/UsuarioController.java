package br.senai.sc.engajamento.usuario.controller;

import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/api/engajamento/usuario")
public class UsuarioController {

    private UsuarioService service;

    @GetMapping("/{usuarioId}")
    public ResponseEntity<Usuario> buscar(
            @Valid @PathVariable String usuarioId
    ) {
        return ResponseEntity.ok(service.retornaUsuario(usuarioId));
    }

}
