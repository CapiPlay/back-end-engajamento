package br.senai.sc.engajamento.usuario.amqp;

import br.senai.sc.engajamento.usuario.amqp.events.AnonimoSalvoEvent;
import br.senai.sc.engajamento.usuario.amqp.events.UsuarioSalvoEvent;
import br.senai.sc.engajamento.usuario.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UsuarioSubscriber {

    private final UsuarioService service;

    @RabbitListener(queues = "usuarios.v1.usuario-salvo.engajamento")
    public void on(UsuarioSalvoEvent event){
        service.handle(event);
    }

    @RabbitListener(queues = "usuarios.v1.anonimo-salvo.engajamento")
    public void on(AnonimoSalvoEvent event){
        service.handle(event);
    }

}
