package br.senai.sc.engajamento.reacoes.repository;

import br.senai.sc.engajamento.reacoes.model.entity.ReacaoRespota;
import br.senai.sc.engajamento.reacoes.model.id.ReacaoRespostaId;
import br.senai.sc.engajamento.resposta.model.entity.Resposta;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReacaoRespostaRepository extends JpaRepository<ReacaoRespota, ReacaoRespostaId> {
    ReacaoRespota findByIdUsuarioAndIdResposta(Usuario idUsuario, Resposta idResposta);

    List<ReacaoRespota> findAllByIdResposta(Resposta idResposta);

    void deleteByIdUsuarioAndIdResposta(Usuario idUsuario, Resposta idResposta);
}
