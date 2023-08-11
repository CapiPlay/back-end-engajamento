package br.senai.sc.engajamento.historico.repository;

import br.senai.sc.engajamento.exception.NaoEncontradoException;
import br.senai.sc.engajamento.historico.model.entity.Historico;
import br.senai.sc.engajamento.historico.model.entity.HistoricoId;
import br.senai.sc.engajamento.inscricao.model.entity.Inscricao;
import br.senai.sc.engajamento.inscricao.model.id.InscricaoId;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.video.model.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, HistoricoId> {

    Historico findByIdUsuarioAndIdVideo(Usuario idUsuario, Video idVideo);

    List<Historico> findAllByIdUsuario(Usuario idUsuario);

    List<Historico> findAllByIdVideo(Video idVideo);

    default Historico getById(HistoricoId id){
        return findById(id).orElseThrow(()-> new NaoEncontradoException("Histórico não encontrada"));
    }
}
