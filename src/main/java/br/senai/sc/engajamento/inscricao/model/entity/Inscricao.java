package br.senai.sc.engajamento.inscricao.model.entity;

import br.senai.sc.engajamento.inscricao.model.id.InscricaoId;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(InscricaoId.class)
public class Inscricao {
    @Id
    @ManyToOne
    private Usuario idUsuario;
    @Id
    @ManyToOne
    private Usuario idCanal;
}
