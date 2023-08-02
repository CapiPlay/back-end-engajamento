package br.senai.sc.engajamento.usuario.model.command;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CriarUsuarioCommand {

    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String foto;
    @Column(nullable = false)
    private int quantidadeInscritos;
}
