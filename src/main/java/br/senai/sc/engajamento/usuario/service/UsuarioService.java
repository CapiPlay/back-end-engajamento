package br.senai.sc.engajamento.usuario.service;

import br.senai.sc.engajamento.exception.NaoEncontradoException;
import br.senai.sc.engajamento.usuario.amqp.events.UsuarioSalvoEvent;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.usuario.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService {
    private final UsuarioRepository repository;

//    public void handle(@Valid UsuarioSalvoEvent event) {
//        repository.findById(event.id()).ifPresentOrElse((usuario) -> {
//            //existe
//            usuario.setNomeCanal(event.nomeCanal());
//            usuario.setNomePerfil(event.nomePerfil());
//            usuario.setFoto(event.foto());
//            usuario.setDescricao(event.descricao());
//            repository.save(usuario);
//        }, () -> {
//            //não existe
//            Usuario usuario = new Usuario();
//            usuario.setIdUsuario(event.id());
//            usuario.setNomeCanal(event.nomeCanal());
//            usuario.setNomePerfil(event.nomePerfil());
//            usuario.setFoto(event.foto());
//            usuario.setQuantidadeInscritos(0);
//            usuario.setDescricao(event.descricao());
//            repository.save(usuario);
//        });
//    }

    // public Usuario retornaUsuario(String idUsuario) {
    //     Optional<Usuario> optionalUsuario = repository.findById(idUsuario);
    //     try {
    //         if (optionalUsuario.isPresent()) {
    //             return optionalUsuario.get();
    //         }
    //         throw new NaoEncontradoException("Usuário não encontrado");
    //     } catch (NaoEncontradoException e) {
    //         System.out.print(e.getMessage());
    //         e.printStackTrace();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     return null;
    // }
}
