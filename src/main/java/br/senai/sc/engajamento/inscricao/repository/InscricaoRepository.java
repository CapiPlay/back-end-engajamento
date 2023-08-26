package br.senai.sc.engajamento.inscricao.repository;

import br.senai.sc.engajamento.exception.NaoEncontradoException;
import br.senai.sc.engajamento.inscricao.model.entity.Inscricao;
import br.senai.sc.engajamento.inscricao.model.id.InscricaoId;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, InscricaoId> {
    Inscricao findByIdUsuarioAndIdCanal(Usuario idUsuario, Usuario idCanal);

    void deleteByIdUsuarioAndIdCanal(Usuario idUsuario, Usuario idCanal);

    default Inscricao getById(InscricaoId id){
        return findById(id).orElseThrow(()-> new NaoEncontradoException("Inscrição não encontrada"));
    }
}
