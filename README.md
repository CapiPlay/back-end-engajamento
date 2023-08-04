## Entidade: Usuário

<h5>obs.: um canal é uma entidade usuário</h5>

Atributos:

- ID de usuário
- Nome
- Foto de perfil
- Inscrições
- Inscritos
- Ativado

## Entidade: Vídeo

Atributos:

- ID de vídeo
- Qtd. visualização
- Qtd. like
- ehInativado

## Entidade: Comentário

Atributos:

- ID do comentário
- Texto
- Data e hora (gerado automaticamente)
- Quantidade de respostas
- ID do usuário que fez o comentário
- ID do vídeo em que o comentário foi feito

## Entidade: Resposta

<h5>obs.: trata-se de uma entidade que lida com as respostas de comentários e também de outras respostas</h5>

Atributos:

- ID da resposta
- Texto
- Data e hora (gerado automaticamente)
- ID do usuário que fez o comentário
- ID do comentário

## Entidade: Inscrição

Atributos:

- ID do usuário que realizou a inscrição
- ID do usuário que recebeu a inscrição

## Entidade: Reação (vídeo)

Atributos:

- ID do usuário que realizou a reação
- ID do vídeo curtido
- Curtida
  <h5>obs.: a curtida é um boolean que quando o atributo existe, quer dizer que há uma reação, se ele for true, quer
  dizer que é uma curtida e se for false quer dizer que é uma descurtida</h5>

## Entidade: Reação Comentário

Atributos:

- ID do usuário que realizou a reação
- ID do comentário curtido
- Curtida
  <h5>obs.: a curtida é um boolean que quando o atributo existe, quer dizer que há uma reação, se ele for true, quer
  dizer que é uma curtida e se for false quer dizer que é uma descurtida</h5>

## Entidade: Reação Resposta

Atributos:

- ID do usuário que realizou a reação
- ID da resposta curtida
- Curtida
  <h5>obs.: a curtida é um boolean que quando o atributo existe, quer dizer que há uma reação, se ele for true, quer
  dizer que é uma curtida e se for false quer dizer que é uma descurtida</h5>

## Entidade: Histórico

Atributos:

- ID do usuário que realizou a visualização
- ID do vídeo visualizado
- Data e hora (gerado automaticamente)

<h5>Qualquer dúvida, tratar com Lucas e Ana </h5>
