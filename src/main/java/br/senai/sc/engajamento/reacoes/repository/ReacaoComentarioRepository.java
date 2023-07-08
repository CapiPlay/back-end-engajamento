package br.senai.sc.engajamento.reacoes.repository;

import br.senai.sc.engajamento.reacoes.model.entity.ReacaoComentario;
import br.senai.sc.engajamento.reacoes.model.id.ReacaoComentarioId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReacaoComentarioRepository extends JpaRepository<ReacaoComentario, ReacaoComentarioId> {
}
