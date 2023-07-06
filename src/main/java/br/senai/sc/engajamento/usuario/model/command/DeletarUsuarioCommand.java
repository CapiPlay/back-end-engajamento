package br.senai.sc.engajamento.usuario.model.command;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class DeletarUsuarioCommand {
    private UUID idUsuario;
}
