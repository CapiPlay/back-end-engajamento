package br.senai.sc.engajamento.reacoes.repository;

import br.senai.sc.engajamento.comentario.model.entity.Comentario;
import br.senai.sc.engajamento.reacoes.model.entity.ReacaoComentario;
import br.senai.sc.engajamento.reacoes.model.id.ReacaoComentarioId;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReacaoComentarioRepository extends JpaRepository<ReacaoComentario, ReacaoComentarioId> {
    ReacaoComentario findByIdUsuarioAndIdComentario(Usuario idUsuario, Comentario idComentario);

    void deleteByIdUsuarioAndIdComentario(Usuario idUsuario, Comentario idComentario);
}
