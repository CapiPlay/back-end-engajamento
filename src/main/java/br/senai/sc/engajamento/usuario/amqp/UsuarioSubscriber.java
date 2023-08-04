package br.senai.sc.engajamento.usuario.amqp;

import br.senai.sc.engajamento.usuario.amqp.events.UsuarioSalvoEvent;
import br.senai.sc.engajamento.usuario.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

@Service
@AllArgsConstructor
public class UsuarioSubscriber {

    private final UsuarioService service;

    @RabbitListener(queues = "usuarios.v1.usuario-salvo.engajamento")
    public void on(@RequestHeader UsuarioSalvoEvent event){
        service.handle(event);
    }

}
