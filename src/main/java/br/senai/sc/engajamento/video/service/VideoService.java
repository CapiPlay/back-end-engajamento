package br.senai.sc.engajamento.video.service;

import br.senai.sc.engajamento.historico.model.entity.Historico;
import br.senai.sc.engajamento.historico.service.HistoricoService;
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
    private HistoricoService historicoService;
//    private Publisher publisher;

//    public void handle(VideoSalvoEvent event) {
//        repository.findById(event.id()).ifPresentOrElse((video) -> {
//            //existe
//            video.setEhInativado(event.ehInativado());
//            repository.save(video);
//        }, () -> {
//            //não existe
//            Video video = new Video(event.id(), event.ehInativado());
//            repository.save(video);
//        });
//    }

    /**
     * 1. Visualizações: peso 1 (cada visualização conta como 1 ponto)
     * 2. Curtidas: peso 2 (cada curtida conta como 2 pontos)
     * 3. Descurtidas: peso -2 (cada descurtida retira 2 pontos)
     * 4. Comentários: peso 3 (cada comentário conta como 3 pontos)
     * 5. Respostas de comentários: peso 2 (cada resposta a um comentário conta como 2 pontos)
     * 6. Cada por cento do vídeo assistido: peso 0.5 (cada 1% do vídeo assistido conta como 0.5 ponto)

     * Calcular a "qualidade" de um vídeo, soma-se todas essas interações multiplicadas por seus respectivos pesos.
     * Ou seja, a qualidade de um vídeo (Q) seria calculada da seguinte maneira:
     * Q = (2 * V - U) + 2*C - 2*D + 3*Co + 2*R + 0.25*P;

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
        List<Historico> listaHistorico = historicoService.buscarTodosPorVideo(video);

        for(Historico historico : listaHistorico){
            percentagemSomadaUsuario += historico.getPercentagemSomada();
            qtdVistaPeloUsuario += historico.getQtdVisualizadas();
        }

        /*Quando um usuário visualiza mais de uma vez o mesmo vídeo a sua pontuação
        é duplicada para cada visualização a partir da primeira*/
        visualizacao =  qtdVistaPeloUsuario * 2L - listaHistorico.size();

        pontuacao = visualizacao + 2 * qtdCurtidas - 2 * qtdDescurtidas + 3 *
                qtdComentarios + 2 * qtdRespostas + 0.25 * percentagemSomadaUsuario;

        video.setPontuacao(pontuacao);
        repository.save(video);
//        publisher.publish(video);
    }

    // public Video retornaVideo(String idVideo) {
    //     Optional<Video> optionalVideo = repository.findById(idVideo);
    //     try {
    //         if (optionalVideo.isPresent()) {
    //             if(!optionalVideo.get().getEhInativado()){
    //                 return optionalVideo.get();
    //             }
    //         }
    //         throw new NaoEncontradoException("Vídeo não encontrado");
    //     } catch (NaoEncontradoException e) {
    //         System.out.print(e.getMessage());
    //         e.printStackTrace();
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     return null;
    // }
}
