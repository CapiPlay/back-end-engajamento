package br.senai.sc.engajamento.comentario.repository;

import br.senai.sc.engajamento.comentario.model.entity.Comentario;
import br.senai.sc.engajamento.exception.NaoEncontradoException;
import br.senai.sc.engajamento.historico.model.entity.Historico;
import br.senai.sc.engajamento.historico.model.entity.HistoricoId;
import br.senai.sc.engajamento.video.model.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, String> {

    List<Comentario> findAllByIdVideo(Video idVideo);

    default Comentario getById(String id){
        return findById(id).orElseThrow(()-> new NaoEncontradoException("Comentário não encontrado"));
    }
}
