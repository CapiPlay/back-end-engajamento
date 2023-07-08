package br.senai.sc.engajamento.reacoes.repository;

import br.senai.sc.engajamento.reacoes.model.entity.ReacaoRespota;
import br.senai.sc.engajamento.reacoes.model.id.ReacaoRespostaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReacaoRespostaRepository extends JpaRepository<ReacaoRespota, ReacaoRespostaId> {
}
