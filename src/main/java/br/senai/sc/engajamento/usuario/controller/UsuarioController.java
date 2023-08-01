package br.senai.sc.engajamento.usuario.controller;

import br.senai.sc.engajamento.usuario.model.command.BuscarQuantidadeInscritosUsuarioCommand;
import br.senai.sc.engajamento.usuario.model.command.EditarQuantidadeInscritosUsuarioCommand;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.usuario.repository.UsuarioRepository;
import br.senai.sc.engajamento.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
@RequestMapping("/api/engajamento/usuario")
public class UsuarioController {

    private UsuarioService service;

    @PostMapping
    public ResponseEntity<Usuario> criar(@Valid @RequestBody Usuario usuario) {
        return ResponseEntity.ok(service.criar(usuario));
    }

    @GetMapping("/buscar-quantidade-inscritos")
    public ResponseEntity<Integer> buscarQuantidadeInscritos(
            @Valid @RequestBody BuscarQuantidadeInscritosUsuarioCommand cmd
    ) {
        return ResponseEntity.ok(service.buscarQuantidadeInscritos(cmd));
    }

    @PutMapping("/editar-quantidade-inscritos")
    public ResponseEntity<Integer> editarQuantidadeInscritos(
            @Valid @RequestBody EditarQuantidadeInscritosUsuarioCommand cmd
    ) {
        return ResponseEntity.ok(service.editarQuantidadeInscritos(cmd));
    }


}
