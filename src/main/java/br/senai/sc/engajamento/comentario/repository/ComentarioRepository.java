package br.senai.sc.engajamento.comentario.repository;

import br.senai.sc.engajamento.comentario.model.entity.Comentario;
import br.senai.sc.engajamento.comentario.service.ComentarioService;
import br.senai.sc.engajamento.video.model.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, String> {

    Optional<Comentario> findByIdComentario(String idComentario);

    List<Comentario> findAllByIdVideo(Video idVideo);
}
