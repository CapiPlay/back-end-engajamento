package br.senai.sc.engajamento.usuario.repository;

import br.senai.sc.engajamento.exception.NaoEncontradoException;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.video.model.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    default Usuario getById(String id){
        return findById(id).orElseThrow(()-> new NaoEncontradoException("Usuário não encontrado"));
    }
}
