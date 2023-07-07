package br.senai.sc.engajamento.reacaoComentario.repository;

import br.senai.sc.engajamento.reacaoComentario.model.entity.ReacaoComentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReacaoComentarioRepository extends JpaRepository<ReacaoComentario, UUID> {
}
