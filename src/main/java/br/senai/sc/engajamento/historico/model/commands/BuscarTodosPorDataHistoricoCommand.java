package br.senai.sc.engajamento.historico.model.commands;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuscarTodosPorDataHistoricoCommand {

    @NotNull
    private String idUsuario;

    @NotNull
    private LocalDate data;

    public BuscarTodosPorDataHistoricoCommand from(String idUsuario) {
        this.idUsuario = idUsuario;
        return this;
    }
}
