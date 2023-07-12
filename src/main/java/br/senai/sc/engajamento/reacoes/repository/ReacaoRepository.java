package br.senai.sc.engajamento.reacoes.repository;

import br.senai.sc.engajamento.reacoes.model.entity.Reacao;
import br.senai.sc.engajamento.reacoes.model.id.ReacaoId;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.video.model.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReacaoRepository extends JpaRepository<Reacao, ReacaoId> {
    Reacao findByIdUsuarioAndIdVideo(Usuario idUsuario, Video idVideo);

    void deleteByIdUsuarioAndIdVideo(Usuario idUsuario, Video idVideo);
}
