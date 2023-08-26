## Comentário

<details>
<summary>Salvar</summary>
  
##### Mapeamento:
```ruby
/api/engajamento/comentario
```
##### Parâmetros/Headers:
```ruby
@RequestHeader String idUsuario,
@RequestBody CriarComentarioCommand cmd

CriarComentarioCommand (
    String idVideo;
    String texto;
)
```

##### Retorno:
```ruby
ResponseEntity<Comentario>

Comentario(
    String idComentario;
    String texto;
    ZonedDateTime dataHora;
    Integer qtdRespostas;
    Usuario idUsuario;
    Video idVideo;
)

```
</details>
<details>
<summary>Buscar um</summary>
  
##### Mapeamento:
```ruby
/api/engajamento/comentario
```
##### Parâmetros/Headers:
```ruby
@RequestBody BuscarUmComentarioCommand cmd

BuscarUmComentarioCommand (
    String idComentario;
)
```

##### Retorno:
```ruby
ResponseEntity<Comentario>

Comentario(
    String idComentario;
    String texto;
    ZonedDateTime dataHora;
    Integer qtdRespostas;
    Usuario idUsuario;
    Video idVideo;
)

```

</details>
<details>
<summary>Buscar todos por vídeo</summary>
  
##### Mapeamento:
```ruby
/api/engajamento/comentario/buscar-todos-por-video/{page}
```
##### Parâmetros/Headers:
```ruby
 @RequestBody BuscarTodosPorVideoComentarioCommand cmd,
 @PathVariable int page

BuscarTodosPorVideoComentarioCommand (
    String idVideo;
)
```

##### Retorno:
```ruby
ResponseEntity<Page<Comentario>>

Comentario(
    String idComentario;
    String texto;
    ZonedDateTime dataHora;
    Integer qtdRespostas;
    Usuario idUsuario;
    Video idVideo;
)
```

</details>
<details>
<summary>Buscar todos por data</summary>
  
##### Mapeamento:
```ruby
/api/engajamento/comentario/buscar-todos-por-data/{page}
```
##### Parâmetros/Headers:
```ruby
@RequestBody BuscarTodosPorDataComentarioCommand cmd,
@PathVariable int page

BuscarTodosPorDataComentarioCommand (
    String idVideo;
    LocalDate data;
)
```

##### Retorno:
```ruby
ResponseEntity<Page<Comentario>>

Comentario(
    String idComentario;
    String texto;
    ZonedDateTime dataHora;
    Integer qtdRespostas;
    Usuario idUsuario;
    Video idVideo;
)

```

</details>
<details>
<summary>Buscar quantidade de respostas</summary>
  
##### Mapeamento:
```ruby
/api/engajamento/comentario/buscar-quantidade-respostas
```
##### Parâmetros/Headers:
```ruby
 @RequestBody BuscarQuantidadeRepostasComentarioCommand cmd

BuscarQuantidadeRepostasComentarioCommand (
    String idComentario;
)
```

##### Retorno:
```ruby
ResponseEntity<Integer>

```

</details>
<details>
<summary>Deletar comentário</summary>
  
##### Mapeamento:
```ruby
/api/engajamento/comentario/buscar-quantidade-respostas
```
##### Parâmetros/Headers:
```ruby
@RequestHeader String idUsuario,
@RequestBody DeletarComentarioCommand cmd

DeletarComentarioCommand (
    String idComentario;
    String idUsuario;
)
```

##### Retorno:
```ruby
ResponseEntity<Void>

```

</details>

<h3>Estamos fazendo ainda :)</h3>

<details>
<summary>Entidades</summary>

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

## Exception: NaoEncontradoException

Construtor:

- Mensagem passada sobre o que não foi encontrado
  <h5>obs.: quando essa exception ocorre, o retorno do endpoint pedido será o erro 404(Not Found)</h5>

## Exception: AcaoNaoPermitidaException

Construtor:

- Mensagem passada sobre qual ação não foi permitido
  <h5>obs.: quando essa exception ocorre, o retorno do endpoint pedido será o erro 403(Forbidden)</h5>

<h5>Qualquer dúvida, tratar com Lucas e Ana </h5>
</details>
