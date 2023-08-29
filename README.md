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
            ResponseEntity<Page<Comentario>
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
            <summary>Deletar</summary>
            
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

## Histórico
        <details>
            <summary>Salvar</summary>

            ##### Mapeamento:
                ```ruby
                /api/engajamento/historico
                ```
            ##### Parâmetros/Headers:
                ```ruby
                @RequestBody CriarHistoricoCommand cmd,
                @RequestHeader("usuarioId") String idUsuario

            CriarHistoricoCommand {
                String idUsuario;
                String idVideo;
                Float percentagemSomada;
                ```
            ##### Retorno:
                ```ruby
                ResponseEntity<Void>
                ```
        </details>

        <details>
            <summary>Buscar um</summary>

            ##### Mapeamento:
                ```ruby
                /api/engajamento/historico
                ```
            ##### Parâmetros/Headers:
                ```ruby
                @RequestBody BuscarUmHistoricoCommand cmd,
                @RequestHeader("usuarioId") String idUsuario

            BuscarUmHistoricoCommand {
                String idUsuario;
                String idVideo;
                ```
            ##### Retorno:
                ```ruby
                ResponseEntity<Historico>

                Historico{
                    Usuario idUsuario;
                    Video idVideo;
                    ZonedDateTime dataHora;
                    Integer qtdVisualizadas;
                    float percentagemSomada;
                }
                ```
        </details>

        <details>
            <summary>Buscar todos históricos por data</summary>

            ##### Mapeamento:
                ```ruby
                /api/engajamento/historico/buscar-todos-históricos-por-data
                ```
            ##### Parâmetros/Headers:
                ```ruby
                @RequestBody BuscarTodosPorDataHistoricoCommand cmd,
                @RequestHeader("usuarioId") String idUsuario

                BuscarUmHistoricoCommand {
                    String idUsuario;
                    LocalDate data;
                ```
            ##### Retorno:
                ```ruby
                ResponseEntity<List<Historico>>

                Historico{
                    Usuario idUsuario;
                    Video idVideo;
                    ZonedDateTime dataHora;
                    Integer qtdVisualizadas;
                    float percentagemSomada;
                }
                ```
        </details>

        <details>
            <summary>Buscar todos históricos por usuário</summary>

            ##### Mapeamento:
                ```ruby
                /api/engajamento/historico/buscar-todos-históricos-por-usuario
                ```
            ##### Parâmetros/Headers:
                ```ruby
                @RequestHeader("usuarioId") String idUsuario
                ```
            ##### Retorno:
                ```ruby
                ResponseEntity<List<Historico>>

                Historico{
                    Usuario idUsuario;
                    Video idVideo;
                    ZonedDateTime dataHora;
                    Integer qtdVisualizadas;
                    float percentagemSomada;
                }
                ```
        </details>

## Inscrição
        <details>
            <summary>Salvar</summary>

            ##### Mapeamento:
                ```ruby
                /api/engajamento/inscricao
                ```
            ##### Parâmetros/Headers:
                ```ruby
                @RequestHeader("usuarioId") String idUsuario,
                @RequestBody CriarInscricaoCommand cmd

                CriarInscricaoCommand {
                    String idUsuario;
                    String idCanal;
                }
                ```
            ##### Retorno:
                ```ruby
                ResponseEntity<Void>
                ```
        </details>

        <details>
            <summary>Buscar um</summary>

            ##### Mapeamento:
                ```ruby
                /api/engajamento/inscricao
                ```
            ##### Parâmetros/Headers:
                ```ruby
                @RequestHeader("usuarioId") String idUsuario,
                @RequestBody BuscarUmInscricaoCommand cmd

                BuscarUmInscricaoCommand {
                    String idUsuario;
                    String idCanal;
                }
                ```
            ##### Retorno:
                ```ruby
                ResponseEntity<Inscricao>

                Inscricao{
                    Usuario idUsuario;
                    Usuario idCanal;  
                }
                ```
        </details>


## Reação Comentário

        <details>
            <summary>Salvar</summary>

            ##### Mapeamento:
                ```ruby
                /api/engajamento/reacaoComentario
                ```
            ##### Parâmetros/Headers:
                ```ruby
                @RequestBody CriarReacaoComentarioCommand cmd,
                @RequestHeader("usuarioId") String idUsuario

                CriarReacaoComentarioCommand {
                    String idUsuario;
                    String idComentario;
                    Boolean curtida;
                }
                ```
            ##### Retorno:
                ```ruby
                ResponseEntity<Void>
                ```
        </details>

        <details>
            <summary>Buscar um</summary>

            ##### Mapeamento:
                ```ruby
                /api/engajamento/reacaoComentario
                ```
            ##### Parâmetros/Headers:
                ```ruby
                @RequestBody BuscarUmReacaoComentarioCommand cmd,
                @RequestHeader("usuarioId") String idUsuario

                BuscarUmReacaoComentarioCommand {
                    String idUsuario;
                    String idComentario;
                }
                ```
            ##### Retorno:
                ```ruby
                ResponseEntity<ReacaoComentario>

                ReacaoComentario {
                    Usuario idUsuario;
                    Comentario idComentario;
                    Boolean curtida;
                }
                ```
        </details>

       <details>
            <summary>Buscar todos por comentário</summary>

            ##### Mapeamento:
                ```ruby
                /api/engajamento/reacaoComentario/buscar-todos-por-comentario
                ```
            ##### Parâmetros/Headers:
                ```ruby
                @RequestBody BuscarTodosPorComentarioReacaoComentarioCommand cmd

                BuscarTodosPorComentarioReacaoComentarioCommand {
                    String idComentario;
                }
                ```
            ##### Retorno:
                ```ruby
                ResponseEntity<List<ReacaoComentario>>

                ReacaoComentario {
                    Usuario idUsuario;
                    Comentario idComentario;
                    Boolean curtida;
                }
                ```
        </details>

## Reação 

        <details>
            <summary>Salvar</summary>

            ##### Mapeamento:
                ```ruby
                /api/engajamento/reacao
                ```
            ##### Parâmetros/Headers:
                ```ruby
                @RequestBody CriarReacaoCommand cmd,
                @RequestHeader("usuarioId") String idUsuario

                CriarReacaoCommand {
                    String idUsuario;
                    String idVideo;
                    Boolean curtida;
                }
                ```
            ##### Retorno:
                ```ruby
                ResponseEntity<Void>
                ```
        </details>

        <details>
            <summary>Buscar um</summary>

            ##### Mapeamento:
                ```ruby
                /api/engajamento/reacao
                ```
            ##### Parâmetros/Headers:
                ```ruby
                @RequestBody BuscarUmReacaoCommand cmd,
                @RequestHeader("usuarioId") String idUsuario

                BuscarUmReacaoCommand {
                    String idUsuario;
                    String idVideo;
                }
                ```
            ##### Retorno:
                ```ruby
                ResponseEntity<Reacao>

                ReacaoComentario {
                    Usuario idUsuario;
                    Video idVideo;
                    Boolean curtida;
                }
                ```
        </details>

       <details>
            <summary>Buscar todos por vídeo</summary>

            ##### Mapeamento:
                ```ruby
                /api/engajamento/reacao/buscar-todos-por-video
                ```
            ##### Parâmetros/Headers:
                ```ruby
                @RequestBody BuscarTodosPorVideoReacaoCommand cmd

                BuscarTodosPorComentarioReacaoComentarioCommand {
                    String idVideo;
                }
                ```
            ##### Retorno:
                ```ruby
                ResponseEntity<List<Reacao>>

                ReacaoComentario {
                    Usuario idUsuario;
                    Video idVideo;
                    Boolean curtida;
                }
                ```
        </details>

## Reação resposta

        <details>
            <summary>Salvar</summary>

            ##### Mapeamento:
                ```ruby
                /api/engajamento/reacaoResposta
                ```
            ##### Parâmetros/Headers:
                ```ruby
                @RequestBody CriarReacaoRespostaCommand cmd,
                @RequestHeader("usuarioId") String idUsuario

                CriarReacaoRespostaCommand {
                    String idUsuario;
                    String idResposta;
                    Boolean curtida;
                }
                ```
            ##### Retorno:
                ```ruby
                ResponseEntity<Void>
                ```
        </details>

        <details>
            <summary>Buscar um</summary>

            ##### Mapeamento:
                ```ruby
                /api/engajamento/reacaoResposta
                ```
            ##### Parâmetros/Headers:
                ```ruby
                @RequestBody BuscarUmReacaoRespostaCommand cmd,
                @RequestHeader("usuarioId") String idUsuario

                BuscarUmReacaoCommand {
                    String idUsuario;
                    String idResposta;
                }
                ```
            ##### Retorno:
                ```ruby
                ResponseEntity<ReacaoResposta>

                ReacaoResposta {
                    Usuario idUsuario;
                    Resposta idResposta;
                    Boolean curtida;
                }
                ```
        </details>

       <details>
            <summary>Buscar todos por comentário</summary>

            ##### Mapeamento:
                ```ruby
                /api/engajamento/reacaoResposta/buscar-todos-por-resposta
                ```
            ##### Parâmetros/Headers:
                ```ruby
                @RequestBody BuscarTodosPorComentarioReacaoRespostaCommand cmd

                BuscarTodosPorComentarioReacaoComentarioCommand {
                    String idResposta;
                }
                ```
            ##### Retorno:
                ```ruby
                ResponseEntity<List<ReacaoResposta>>

                ReacaoResposta {
                    Usuario idUsuario;
                    Resposta idResposta;
                    Boolean curtida;
                }
                ```
        </details>

## Resposta

        <details>
            <summary>Salvar</summary>

            ##### Mapeamento:
                ```ruby
                /api/engajamento/resposta
                ```
            ##### Parâmetros/Headers:
                ```ruby
                @RequestBody CriarRespostaCommand cmd,
                @RequestHeader("usuarioId") String idUsuario

                CriarRespostaCommand {
                    String idUsuario;
                    String idComentario;
                    String texto;
                }
                ```
            ##### Retorno:
                ```ruby
                ResponseEntity<Resposta>

                Resposta{
                    String idResposta;
                    String texto;
                    ZonedDateTime dataHora;
                    Usuario idUsuario;
                    Comentario idComentario;
                }
                ```
        </details>


        <details>
            <summary>Buscar um</summary>

            ##### Mapeamento:
                ```ruby
                /api/engajamento/resposta
                ```
            ##### Parâmetros/Headers:
                ```ruby
                @RequestBody BuscarUmaRespostaCommand cmd

                BuscarUmaRespostaCommand {
                    String idResposta;
                }
                ```
            ##### Retorno:
                ```ruby
                ResponseEntity<Resposta>

                Resposta{
                    String idResposta;
                    String texto;
                    ZonedDateTime dataHora;
                    Usuario idUsuario;
                    Comentario idComentario;
                }
                ```
        </details>

        <details>
            <summary>Buscar todos por comentário</summary>

            ##### Mapeamento:
                ```ruby
                /api/engajamento/resposta/buscar-todos-por-comentario/{page}
                ```
            ##### Parâmetros/Headers:
                ```ruby
                @RequestBody BuscarTodosPorComentarioRespostaCommand cmd,
                @PathVariable int page

                BuscarTodosPorComentarioRespostaCommand {
                    String idComentario;
                }
                ```
            ##### Retorno:
                ```ruby
                ResponseEntity<Page<Resposta>>

                Resposta{
                    String idResposta;
                    String texto;
                    ZonedDateTime dataHora;
                    Usuario idUsuario;
                    Comentario idComentario;
                }
                ```
        </details>

        <details>
            <summary>Deletar</summary>

            ##### Mapeamento:
                ```ruby
                /api/engajamento/resposta
                ```
            ##### Parâmetros/Headers:
                ```ruby
                @RequestHeader("usuarioId") String idUsuario,
                @RequestBody DeletarRespostaCommand cmd

                DeletarRespostaCommand {
                    String idUsuario;
                    String idResposta;
                }
                ```
            ##### Retorno:
                ```ruby
                ResponseEntity<Void>
                ```
        </details>

## Lógica pontuação vídeo

        <details>
            <summary>Deletar</summary>

            1. Visualizações: peso 1 (cada visualização conta como 1 ponto)
            2. Curtidas: peso 2 (cada curtida conta como 2 pontos)
            3. Descurtidas: peso -2 (cada descurtida retira 2 pontos)
            4. Comentários: peso 3 (cada comentário conta como 3 pontos)
            5. Respostas de comentários: peso 2 (cada resposta a um comentário conta como 2 pontos)
            6. Cada por cento do vídeo assistido: peso 0.5 (cada 1% do vídeo assistido conta como 0.5 ponto)

            Calcular a "qualidade" de um vídeo, soma-se todas essas interações multiplicadas por seus respectivos pesos.
            Ou seja, a qualidade de um vídeo (Q) seria calculada da seguinte maneira:
            Q = (V - 0.5 * U) + C - D + 1.5*Co + R + 0.175*P;

            Onde:
            - V é o número de visualizações;
            - C é o número de curtidas;
            - D é o número de descurtidas;
            - Co é o número de comentários;
            - R é o número de respostas de comentários;
            - P é o número total de percentagem do vídeo assistido;
            - U é a quantidade de usuários que assistiram o vídeo.
        </details>


  <h5>obs.: a curtida é um boolean que quando o atributo existe, quer dizer que há uma reação, se ele for true, quer
  dizer que é uma curtida e se for false quer dizer que é uma descurtida</h5>
