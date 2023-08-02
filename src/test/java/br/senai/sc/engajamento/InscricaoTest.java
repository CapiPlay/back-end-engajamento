package br.senai.sc.engajamento;

import br.senai.sc.engajamento.inscricao.model.command.BuscarUmInscricaoCommand;
import br.senai.sc.engajamento.inscricao.model.command.CriarInscricaoCommand;
import br.senai.sc.engajamento.inscricao.model.entity.Inscricao;
import br.senai.sc.engajamento.inscricao.service.InscricaoService;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.usuario.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@AllArgsConstructor
public class InscricaoTest {
    private InscricaoService inscricaoService;
    private UsuarioService usuarioService;
    private final Usuario usuario = new Usuario();
    private final Usuario usuario2 = new Usuario();
    private Inscricao inscricao;

    @BeforeEach
    public void setup() {
        inscricao.setIdUsuario(usuario);
        inscricao.setIdCanal(usuario2);
    }

    @Test
    public void criar() {
        usuarioService.criar(usuario);
        usuarioService.criar(usuario2);
        inscricaoService.criar(new CriarInscricaoCommand(usuario.getIdUsuario(), usuario2.getIdUsuario()));

        assertEquals(inscricao, inscricaoService.buscarUm(new BuscarUmInscricaoCommand(usuario.getIdUsuario(), usuario2.getIdUsuario())));
        inscricaoService.criar(new CriarInscricaoCommand(usuario.getIdUsuario(), usuario2.getIdUsuario()));

        assertNull(inscricaoService.buscarUm(new BuscarUmInscricaoCommand(usuario.getIdUsuario(), usuario2.getIdUsuario())));
    }
}
