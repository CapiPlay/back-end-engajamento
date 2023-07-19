package br.senai.sc.engajamento.video.controller;

import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.video.model.entity.Video;
import br.senai.sc.engajamento.video.repository.VideoRepository;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/api/engajamento/video")
public class VideoController {
    private VideoRepository repository;

    @PostMapping
    private ResponseEntity<Video> criar(@Valid @RequestBody Video video) {
        Video videoNovo = new Video();
        BeanUtils.copyProperties(video, videoNovo);
        return ResponseEntity.ok(repository.save(videoNovo));
    }
}
