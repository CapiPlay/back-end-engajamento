package br.senai.sc.engajamento.video.service;

import br.senai.sc.engajamento.exception.NaoEncontradoException;
import br.senai.sc.engajamento.messaging.Publisher;
import br.senai.sc.engajamento.video.amqp.events.VideoSalvoEvent;
import br.senai.sc.engajamento.video.model.entity.Video;
import br.senai.sc.engajamento.video.repository.VideoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VideoService {
    private final VideoRepository repository;
    private final Publisher publisher;

    public Video retornaVideo(String idVideo) {
        Optional<Video> optionalVideo = repository.findById(idVideo);
        try {
            if (optionalVideo.isPresent()) {
                return optionalVideo.get();
            }
            throw new NaoEncontradoException("Vídeo não encontrado");
        } catch (NaoEncontradoException e) {
            System.out.print(e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void handle(VideoSalvoEvent event) {
        repository.findById(event.id()).ifPresentOrElse((video) -> {
            //existe
            video.setId(event.id());
            video.setEhInativado(event.ehInativado());
            video.setQtdCurtidas(0L);
            video.setQtdDescurtidas(0L);
            video.setQtdComentarios(0L);
            video.setQtdRespostas(0L);
            video.setPontuacao(0.0);
            repository.save(video);
        }, () -> {
            //não existe
            Video video = new Video(event.id(), event.ehInativado());
            repository.save(video);
        });
    }


    public void editarPontuacao(Video video) {
        Double pontuacao = video.getPontuacao();
        Long visualizacao = video.getVisualizacao();
        Long qtdCurtidas = video.getQtdCurtidas();
        Long qtdDescurtidas = video.getQtdDescurtidas();
        Long qtdComentarios = video.getQtdComentarios();
        Long qtdRespostas = video.getQtdRespostas();
        /*
            1. Visualizações: peso 1 (cada visualização conta como 1 ponto)
            2. Curtidas: peso 2 (cada curtida conta como 2 pontos)
            3. Descurtidas: peso -2 (cada descurtida retira 2 pontos)
            4. Comentários: peso 3 (cada comentário conta como 3 pontos)
            5. Respostas de comentários: peso 2 (cada resposta a um comentário conta como 2 pontos)
            6. Cada por cento do vídeo assistido: peso 0.5 (cada 1% do vídeo assistido conta como 0.5 ponto)

            Calcular a "qualidade" de um vídeo, soma-se todas essas interações multiplicadas por seus respectivos pesos.
            Ou seja, a qualidade de um vídeo (Q) seria calculada da seguinte maneira:

            Q = V + 2*C - 2*D + 3*Co + 2*R + 0.5*P

            Onde:
            - V é o número de visualizações,
            - C é o número de curtidas,
            - D é o número de descurtidas,
            - Co é o número de comentários,
            - R é o número de respostas de comentários,
            - P é o número total de percentagem do vídeo assistido.
         */

        pontuacao = visualizacao + 2 * qtdCurtidas - 2 * qtdDescurtidas + 3 *
                qtdComentarios + 2 * qtdRespostas + 0.5 * /*ver depois a percentagem*/

                video.setPontuacao();
    }

    public void enviarVideo(Video video) {
        publisher.publish(video);
    }
}
