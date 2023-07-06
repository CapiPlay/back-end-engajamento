package br.senai.sc.engajamento.usuario.model.command;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BuscarUmUsuarioPorNomeCommand {
    private String nome;
}
