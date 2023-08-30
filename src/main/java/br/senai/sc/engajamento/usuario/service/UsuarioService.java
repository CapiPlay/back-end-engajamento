package br.senai.sc.engajamento.usuario.service;

import br.senai.sc.engajamento.usuario.amqp.events.AnonimoSalvoEvent;
import br.senai.sc.engajamento.usuario.amqp.events.UsuarioSalvoEvent;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.usuario.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;

    public void handle(UsuarioSalvoEvent event) {

        Usuario usuario = repository.findById(event.id())
                .map(event::editar)
                .orElseGet(event::criar);

        salvar(usuario);
    }

    public void handle(AnonimoSalvoEvent event) {
        repository.findById(event.id())
                .orElseGet(()-> salvar(event.criar()));
    }

    private Usuario salvar(Usuario usuario) {
        return repository.save(usuario);
    }

    public Usuario retornaUsuario(String uuid){
        return repository.getById(uuid);
    }
}
