## Entidade: Usuário

Atributos:
- Nome
- ID de usuário
- Senha
- Endereço de e-mail
- Foto de perfil
- Data de registro
- Lista de vídeos favoritos
- Lista de canais inscritos
- Lista de comentários feitos

## Entidade: Vídeo

Atributos:
- Título
- Descrição
- URL do vídeo
- Data de upload
- Número de visualizações
- Número de curtidas
- Número de descurtidas
- Lista de comentários
- Duração
- Categoria

## Entidade: Canal

Atributos:
- Nome
- Descrição
- URL do canal
- Data de criação
- Número de inscritos
- Lista de vídeos do canal
- Lista de playlists

## Entidade: Comentário

Atributos:
- Texto do comentário
- Data e hora do comentário
- Número de curtidas
- Número de descurtidas
- ID do usuário que fez o comentário
- ID do vídeo em que o comentário foi feito

## Entidade: Curtida

Atributos:
- ID do usuário que realizou a curtida
- ID do vídeo curtido
- Data e hora da curtida
