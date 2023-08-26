package br.senai.sc.engajamento.video.service;

import br.senai.sc.engajamento.historico.model.entity.Historico;
import br.senai.sc.engajamento.historico.repository.HistoricoRepository;
import br.senai.sc.engajamento.messaging.Publisher;
import br.senai.sc.engajamento.video.amqp.events.VideoAtualizadoEvent;
import br.senai.sc.engajamento.video.amqp.events.VideoSalvoEvent;
import br.senai.sc.engajamento.video.model.entity.Video;
import br.senai.sc.engajamento.video.repository.VideoRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class VideoService {
    private VideoRepository repository;
    private HistoricoRepository historicoRepository;
    private Publisher publisher;

    public void handle(VideoSalvoEvent event) {
        repository.findById(event.id()).ifPresentOrElse((video) -> {
            //existe
            video.setEhInativado(event.ehInativado());
            repository.save(video);
        }, () -> {
            //não existe
            Video video = new Video(event.id(), event.ehInativado());
            repository.save(video);
        });
    }

    /**
     * 1. Visualizações: peso 1 (cada visualização conta como 1 ponto)
     * 2. Curtidas: peso 2 (cada curtida conta como 2 pontos)
     * 3. Descurtidas: peso -2 (cada descurtida retira 2 pontos)
     * 4. Comentários: peso 3 (cada comentário conta como 3 pontos)
     * 5. Respostas de comentários: peso 2 (cada resposta a um comentário conta como 2 pontos)
     * 6. Cada por cento do vídeo assistido: peso 0.5 (cada 1% do vídeo assistido conta como 0.5 ponto)

     * Calcular a "qualidade" de um vídeo, soma-se todas essas interações multiplicadas por seus respectivos pesos.
     * Ou seja, a qualidade de um vídeo (Q) seria calculada da seguinte maneira:
     * Q = (V - 0.5 * U) + C - D + 1.5*Co + R + 0.175*P;

     * Onde:
     * - V é o número de visualizações;
     * - C é o número de curtidas;
     * - D é o número de descurtidas;
     * - Co é o número de comentários;
     * - R é o número de respostas de comentários;
     * - P é o número total de percentagem do vídeo assistido;
     * - U é a quantidade de usuários que assistiram o vídeo.
    **/
    public void editarPontuacao(@Valid Video video) {
        double pontuacao;
        long visualizacao;
        Long qtdCurtidas = video.getQtdCurtidas();
        Long qtdDescurtidas = video.getQtdDescurtidas();
        Long qtdComentarios = video.getQtdComentarios();
        Long qtdRespostas = video.getQtdRespostas();
        float percentagemSomadaUsuario = 0f;
        Integer qtdVistaPeloUsuario = 0;

        /*Calculo da percentagem*/
        List<Historico> listaHistorico = historicoRepository.findAllByIdVideo(video);

        for(Historico historico : listaHistorico){
            percentagemSomadaUsuario += historico.getPercentagemSomada();
            qtdVistaPeloUsuario += historico.getQtdVisualizadas();
        }
        video.setVisualizacao(qtdVistaPeloUsuario.longValue());

        /*Quando um usuário visualiza mais de uma vez o mesmo vídeo a sua pontuação
        é duplicada para cada visualização a partir da primeira*/
        visualizacao =  qtdVistaPeloUsuario * 2L - (listaHistorico.size());

        pontuacao = visualizacao * 0.5 + qtdCurtidas - qtdDescurtidas + 1.5 *
                qtdComentarios + qtdRespostas + 0.175 * percentagemSomadaUsuario;

        video.setPontuacao(pontuacao);
        repository.save(video);
        VideoAtualizadoEvent videoEvent = new VideoAtualizadoEvent(
                video.getId(), visualizacao,
                qtdCurtidas, qtdComentarios,
                pontuacao
        );
        publisher.publish(videoEvent);
    }
}
