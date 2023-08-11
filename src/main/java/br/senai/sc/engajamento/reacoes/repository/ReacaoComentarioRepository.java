package br.senai.sc.engajamento.reacoes.repository;

import br.senai.sc.engajamento.comentario.model.entity.Comentario;
import br.senai.sc.engajamento.exception.NaoEncontradoException;
import br.senai.sc.engajamento.reacoes.model.entity.ReacaoComentario;
import br.senai.sc.engajamento.reacoes.model.id.ReacaoComentarioId;
import br.senai.sc.engajamento.resposta.model.entity.Resposta;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReacaoComentarioRepository extends JpaRepository<ReacaoComentario, ReacaoComentarioId> {
    ReacaoComentario findByIdUsuarioAndIdComentario(Usuario idUsuario, Comentario idComentario);

    List<ReacaoComentario> findAllByIdComentario(Comentario idComentario);

    void deleteByIdUsuarioAndIdComentario(Usuario idUsuario, Comentario idComentario);

    default ReacaoComentario getById(ReacaoComentarioId id){
        return findById(id).orElseThrow(()-> new NaoEncontradoException("Reação do comentário não encontrada"));
    }
}
