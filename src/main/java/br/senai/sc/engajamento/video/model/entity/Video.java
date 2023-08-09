package br.senai.sc.engajamento.video.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Video {
    @Id
    @Column
    private String id;

    @Column(nullable = false)
    private Long visualizacao;

    @Column(nullable = false)
    private Long qtdCurtidas;

    @Column(nullable = false)
    private Long qtdDescurtidas;

    @Column(nullable = false)
    private Long qtdComentarios;

    @Column(nullable = false)
    private Long qtdRespostas;

    @Column(nullable = false)
    private Boolean ehInativado;

    @Column(nullable = false)
    private Double pontuacao;

    @Column(nullable = false)
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
    }
}