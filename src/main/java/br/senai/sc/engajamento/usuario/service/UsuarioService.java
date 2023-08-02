package br.senai.sc.engajamento.usuario.service;

import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.usuario.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {
    private final UsuarioRepository repository;

    public Usuario retornaUsuario(String idUsuario) {
        return repository.findById(idUsuario).orElseThrow();
    }
}
