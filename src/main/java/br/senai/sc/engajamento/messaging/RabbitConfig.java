package br.senai.sc.engajamento.messaging;

import br.senai.sc.engajamento.video.amqp.events.VideoAtualizadoEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitConfig {

    private final ConnectionFactory connectionFactory;

    @Bean
    TopicExchange exchange(){
        return new TopicExchange("engajamento-service");
    }

    @Bean
    Queue usuarioSalvoEngajamentoQueue() {
        return new Queue("usuarios.v1.usuario-salvo.engajamento");
    }

    @Bean
    Queue videoAtualizadoVideoQueue() {
        return new Queue("videos.v1.video-atualizado.video");
    }

    @Bean
    Queue videoSalvoEngajamentoQueue() {
        return new Queue("videos.v1.video-salvo.engajamento");
    }

    @Bean
    Binding bindingVideoAtualizadoVideoEvent(Queue videoAtualizadoVideoQueue, TopicExchange exchange) {
        return BindingBuilder.bind(videoAtualizadoVideoQueue).to(exchange).with(VideoAtualizadoEvent.class.getSimpleName());
    }

    @Bean
    public RabbitTemplate rabbitTemplate(){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }

    @Bean
    public MessageConverter jsonMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
