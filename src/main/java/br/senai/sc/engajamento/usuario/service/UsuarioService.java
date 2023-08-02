package br.senai.sc.engajamento.usuario.service;

import br.senai.sc.engajamento.exception.NaoEncontradoException;
import br.senai.sc.engajamento.usuario.model.command.BuscarQuantidadeInscritosUsuarioCommand;
import br.senai.sc.engajamento.usuario.model.command.EditarQuantidadeInscritosUsuarioCommand;
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

    public Usuario criar(Usuario usuario){
        return null;
    }

    public Integer buscarQuantidadeInscritos(BuscarQuantidadeInscritosUsuarioCommand cmd){
        return null;
    }

    public Integer editarQuantidadeInscritos(EditarQuantidadeInscritosUsuarioCommand cmd){
        return null;
    }

    public Usuario retornaUsuario(String idUsuario) {
        Optional<Usuario> optionalUsuario = repository.findById(idUsuario);
        if(optionalUsuario.isPresent()){
            return optionalUsuario.get();
        }
        throw new NaoEncontradoException("Usuário encontrado");
    }
}
