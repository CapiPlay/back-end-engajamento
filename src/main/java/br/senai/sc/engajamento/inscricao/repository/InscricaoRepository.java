package br.senai.sc.engajamento.inscricao.repository;

import br.senai.sc.engajamento.inscricao.model.entity.Inscricao;
import br.senai.sc.engajamento.inscricao.model.id.InscricaoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, InscricaoId> {
}
