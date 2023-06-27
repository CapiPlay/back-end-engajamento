## Entidade: Usuário
Atributos:
- ID de usuário
- Nome
- Foto de perfil

## Entidade: Vídeo
Atributos:
- ID de vídeo
- Visualizações (Entidade)
- Curtidas (Entidade)
- Descurtidas (Entidade)
- Comentários (Entidade)

## Entidade: Canal
Atributos:
- ID do canal
- Inscritos (Entidade) 

## Entidade: Comentário
Atributos:
- Texto do comentário
- Data e hora do comentário
- Número de curtidas (Entidade curtida)
- Número de descurtidas
- ID do usuário que fez o comentário
- ID do vídeo em que o comentário foi feito

## Entidade: Inscritos
Atributos:
- ID do usuário que realizou a inscrição
- ID do canal

## Entidade: Curtida
Atributos:
- ID do usuário que realizou a curtida
- ID do item curtido

## Entidade: Descurtida
Atributos:
- ID do usuário que realizou a descurtido
- ID do vídeo descurtido

## Entidade: Compartilhamento
Atributos:
- ID do usuário que realizou o compartilhamento
- ID do item compartilhado
