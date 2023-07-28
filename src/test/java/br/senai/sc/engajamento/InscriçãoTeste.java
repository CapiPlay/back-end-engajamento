package br.senai.sc.engajamento;

import br.senai.sc.engajamento.inscricao.controller.InscricaoController;
import br.senai.sc.engajamento.inscricao.model.command.BuscarUmInscricaoCommand;
import br.senai.sc.engajamento.inscricao.model.command.CriarInscricaoCommand;
import br.senai.sc.engajamento.inscricao.model.entity.Inscricao;
import br.senai.sc.engajamento.usuario.controller.UsuarioController;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Data
@AllArgsConstructor
public class InscriçãoTeste {

    private InscricaoController inscricaoController;
    private UsuarioController usuarioController;
    private final Usuario usuario = new Usuario();
    private final Usuario usuario2 = new Usuario();
    private Inscricao inscricao;


    @BeforeEach
    public void criar(){
        usuarioController.criar(usuario);
        usuarioController.criar(usuario2);
        inscricaoController.criar(
                new CriarInscricaoCommand(usuario.getIdUsuario(), usuario2.getIdUsuario())
        );
    }

    @Test
    public void buscarUm(){
        inscricaoController.buscarUm(
                new BuscarUmInscricaoCommand(usuario.getIdUsuario(), usuario2.getIdUsuario())
        ).getBody();
    }
}
