package br.senai.sc.engajamento.video.amqp;

import br.senai.sc.engajamento.video.amqp.events.VideoSalvoEvent;
import br.senai.sc.engajamento.video.service.VideoService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

@Service
@AllArgsConstructor
public class VideoSubscriber {
    private final VideoService service;

    @RabbitListener(queues = "videos.v1.video-salvo.engajamento")
    public void on(VideoSalvoEvent event){
        service.handle(event);
    }

}
