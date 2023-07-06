package br.senai.sc.engajamento.inscricao.model.entity;

import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Inscricao {
    @Id
    @Column(columnDefinition = "char(36)")
    private UUID uuid;
    @ManyToOne
    private Usuario usuario;
    @ManyToOne
    private Usuario canal;
}
