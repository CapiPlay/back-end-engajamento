package br.senai.sc.engajamento.usuario.model.command;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;

@Data
@AllArgsConstructor
public class CriarUsuarioCommand {
    private String nome;
    private File foto;
}
