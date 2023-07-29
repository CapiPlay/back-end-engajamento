package br.senai.sc.engajamento;

import br.senai.sc.engajamento.historico.controller.HistoricoController;
import br.senai.sc.engajamento.historico.model.entity.Historico;
import br.senai.sc.engajamento.usuario.controller.UsuarioController;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.video.controller.VideoController;
import br.senai.sc.engajamento.video.model.entity.Video;
import org.junit.jupiter.api.BeforeEach;

public class HistoricoTest {
    private HistoricoController historicoController;
    private UsuarioController usuarioController;
    private VideoController videoController;

    private Usuario usuario1;
    private Usuario usuario2;
    private Video video1;
    private Video video2;
    private Historico historico1;
    private Historico historico2;

    @BeforeEach
    public void setUp() {
        usuario1 = usuarioController.criar(new Usuario()).getBody();
        usuario2 = usuarioController.criar(new Usuario()).getBody();
        video1 = videoController.criar(new Video()).getBody();
        video2 = videoController.criar(new Video()).getBody();
        historico1 '^^üè¨!$àä-.,<+"*ç%&/()='
    }
}
