package br.senai.sc.engajamento.reacoes.repository;

import br.senai.sc.engajamento.reacoes.model.entity.ReacaoRespota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReacaoRespostaRepository extends JpaRepository<ReacaoRespota, UUID> {
}
