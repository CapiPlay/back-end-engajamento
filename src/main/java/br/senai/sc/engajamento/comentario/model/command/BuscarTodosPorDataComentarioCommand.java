package br.senai.sc.engajamento.comentario.model.command;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuscarTodosPorDataComentarioCommand {

    @NotNull
    private String idVideo;

    @NotNull
    private LocalDate data;

}
