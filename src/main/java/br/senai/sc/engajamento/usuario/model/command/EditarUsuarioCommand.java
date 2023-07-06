package br.senai.sc.engajamento.usuario.model.command;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.File;
import java.util.UUID;

@Data
@AllArgsConstructor
public class EditarUsuarioCommand {
    private UUID idUsuario;
    private String nome;
    private File foto;
}
