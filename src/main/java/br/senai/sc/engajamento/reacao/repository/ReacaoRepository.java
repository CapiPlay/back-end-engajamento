package br.senai.sc.engajamento.reacao.repository;

import br.senai.sc.engajamento.reacao.model.entity.Reacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReacaoRepository extends JpaRepository<Reacao, Long> {
}
