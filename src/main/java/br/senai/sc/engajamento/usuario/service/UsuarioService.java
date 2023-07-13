package br.senai.sc.engajamento.usuario.service;

import br.senai.sc.engajamento.exception.NaoEncontradoException;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.usuario.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UsuarioService {
    private final UsuarioRepository repository;

    public Usuario retornaUsuario(UUID idUsuario) {
        Optional<Usuario> usuario = repository.findById(idUsuario);
        if (usuario.isPresent()) {
            return usuario.get();
        }
        throw new NaoEncontradoException();
    }
}
