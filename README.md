## Entidade: Usuário
Atributos:
- ID de usuário
- Nome
- Foto de perfil
- Histórico (Entidade)

## Entidade: Vídeo
Atributos:
- ID de vídeo
- Visualização
- Reação (Entidade)
- Comentários (Entidade)

## Entidade: Canal
Atributos:
- ID do canal
- Inscrição (Entidade) 

## Entidade: Comentário
Atributos:
- ID do comentário
- Texto 
- Data e hora 
- Número de reação-comentário (Entidade)
- Número de respostas
- ID do usuário que fez o comentário
- ID do vídeo em que o comentário foi feito

## Entidade: Inscrição
Atributos:
- ID do usuário que realizou a inscrição
- ID do canal

## Entidade: Reação
Atributos:
- ID do usuário que realizou a reação
- Curtida - boolean
- ID do item curtido

## Entidade: Histórico
Atributos:
- ID do usuário que realizou a visualização
- ID do vídeo visualizado
- Data e hora de visualização
