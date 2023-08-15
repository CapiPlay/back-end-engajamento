package br.senai.sc.engajamento.resposta.repository;

import br.senai.sc.engajamento.comentario.model.entity.Comentario;
import br.senai.sc.engajamento.exception.NaoEncontradoException;
import br.senai.sc.engajamento.resposta.model.entity.Resposta;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespostaRepository extends JpaRepository<Resposta, String> {
    Page<Resposta> findAllByIdComentarioOrderByDataHora(Comentario idComentario, Pageable pageable);
    default Resposta getById(String id){
        return findById(id).orElseThrow(()-> new NaoEncontradoException("Resposta não encontrado"));
    }
}
