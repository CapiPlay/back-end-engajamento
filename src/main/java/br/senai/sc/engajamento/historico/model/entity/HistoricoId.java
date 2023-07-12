package br.senai.sc.engajamento.historico.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoricoId implements Serializable {

    private UUID idUsuario;
    private UUID idVideo;

}
