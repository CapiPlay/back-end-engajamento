package br.senai.sc.engajamento.video.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Video {
    @Id
    @Column
    private String id;

    private Long visualizacao;

    private Long qtdCurtidas;

    private Long qtdDescurtidas;

    private Long qtdComentarios;

    private Long qtdRespostas;

    @Column(nullable = false)
    private Boolean ehInativado;

    private Double pontuacao;

    private Double percentagemSomada;

    public Video(String id, Boolean ehInativado) {
        this.id = id;
        this.visualizacao = 0L;
        this.qtdCurtidas = 0L;
        this.qtdDescurtidas = 0L;
        this.qtdComentarios = 0L;
        this.qtdRespostas = 0L;
        this.ehInativado = ehInativado;
        this.pontuacao = 0.0;
        this.percentagemSomada = 0.0;
    }
}