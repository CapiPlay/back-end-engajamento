package br.senai.sc.engajamento.inscricao.model.entity;

import br.senai.sc.engajamento.inscricao.model.id.InscricaoId;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@IdClass(InscricaoId.class)
public class Inscricao {

    @Id
    @ManyToOne
    @Cascade(CascadeType.ALL)
    @JoinColumn(nullable = false, name = "id_usuario")
    private Usuario idUsuario;

    @Id
    @ManyToOne
    @Cascade(CascadeType.ALL)
    @JoinColumn(nullable = false, name = "id_canal")
    private Usuario idCanal;
}
