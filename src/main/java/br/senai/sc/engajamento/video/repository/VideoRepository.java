package br.senai.sc.engajamento.video.repository;

import br.senai.sc.engajamento.exception.NaoEncontradoException;
import br.senai.sc.engajamento.video.model.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VideoRepository extends JpaRepository<Video, String>, CrudRepository<Video, String> {

    default Video getById(String id){
        return findById(id).orElseThrow(()-> new NaoEncontradoException("Vídeo não encontrado"));
    }
}
