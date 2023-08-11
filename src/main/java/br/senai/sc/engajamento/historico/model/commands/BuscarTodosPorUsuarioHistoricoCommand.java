package br.senai.sc.engajamento.historico.model.commands;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuscarTodosPorUsuarioHistoricoCommand {

    @NotNull(message = "ID do usuário não informado")
    private String idUsuario;
}