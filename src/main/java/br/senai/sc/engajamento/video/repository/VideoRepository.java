package br.senai.sc.engajamento.video.repository;

import br.senai.sc.engajamento.video.model.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepository extends JpaRepository<Video, String> {}
