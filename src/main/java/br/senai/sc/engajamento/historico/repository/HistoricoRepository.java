package br.senai.sc.engajamento.historico.repository;

import br.senai.sc.engajamento.historico.model.entity.Historico;
import br.senai.sc.engajamento.historico.model.entity.HistoricoId;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.video.model.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HistoricoRepository extends JpaRepository<Historico, HistoricoId> {

    Historico findByIdUsuarioAndIdVideo(Usuario usuario, Video video);
}
