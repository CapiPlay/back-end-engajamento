package br.senai.sc.engajamento.video.service;

import br.senai.sc.engajamento.exception.NaoEncontradoException;
import br.senai.sc.engajamento.video.amqp.events.VideoSalvoEvent;
import br.senai.sc.engajamento.video.model.entity.Video;
import br.senai.sc.engajamento.video.repository.VideoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class VideoService {
    private final VideoRepository repository;

    public Video criar(Video video){
        return repository.save(video);
    }

    public Video retornaVideo(String idVideo) {
        Optional<Video> optionalVideo = repository.findById(idVideo);
        try {
            if (optionalVideo.isPresent()) {
                return optionalVideo.get();
            }
            throw new NaoEncontradoException("Vídeo não encontrado");
        }catch (NaoEncontradoException e){
            System.out.print(e.getMessage());
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void handle(VideoSalvoEvent event) {
        repository.findById(event.id()).ifPresentOrElse((video)-> {
            //existe
            BeanUtils.copyProperties(event, video);
            repository.save(video);
        }, ()-> {
            //não existe
            Video video = new Video(event);
            repository.save(video);
        });
    }
}
