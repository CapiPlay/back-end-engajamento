package br.senai.sc.engajamento.usuario.controller;

import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.usuario.repository.UsuarioRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/api/engajamento/usuario")
public class UsuarioController {
    private UsuarioRepository repository;

    @PostMapping
    private ResponseEntity<Usuario> criar(@Valid @RequestBody Usuario usuario) {
        Usuario usuarioNovo = new Usuario();
        BeanUtils.copyProperties(usuario, usuarioNovo);
        return ResponseEntity.ok(repository.save(usuarioNovo));
    }
}
