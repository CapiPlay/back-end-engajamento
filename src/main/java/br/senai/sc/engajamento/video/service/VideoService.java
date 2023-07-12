package br.senai.sc.engajamento.video.service;

import br.senai.sc.engajamento.video.model.entity.Video;
import br.senai.sc.engajamento.video.repository.VideoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class VideoService {
    private final VideoRepository repository;

    public Video retornaVideo(UUID idVideo) {
        return repository.findById(idVideo).orElseThrow();
    }
}
