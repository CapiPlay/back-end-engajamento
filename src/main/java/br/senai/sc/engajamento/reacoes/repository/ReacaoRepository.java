package br.senai.sc.engajamento.reacoes.repository;

import br.senai.sc.engajamento.reacoes.model.entity.Reacao;
import br.senai.sc.engajamento.reacoes.model.id.ReacaoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReacaoRepository extends JpaRepository<Reacao, ReacaoId> {
}
