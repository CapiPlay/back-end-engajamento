package br.senai.sc.engajamento.video.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

import static java.util.Objects.requireNonNullElse;

@Data
@Entity
@NoArgsConstructor
public class Video {
    @Id
    @Column
    private String id;

    private long visualizacao;

    private long qtdCurtidas;

    private long qtdDescurtidas;

    private long qtdComentarios;

    private long qtdRespostas;

    private Boolean ehInativado;

    private Double pontuacao;

    private Double percentagemSomada;

    public void setEhInativado(Boolean ehInativado) {
        this.ehInativado = requireNonNullElse(ehInativado, false);
    }

    public Video(String id) {
        this.id = id;
        this.ehInativado = false;
    }

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