package br.senai.sc.engajamento.inscricao.service;

import br.senai.sc.engajamento.inscricao.model.command.BuscarUmInscricaoCommand;
import br.senai.sc.engajamento.inscricao.model.command.CriarInscricaoCommand;
import br.senai.sc.engajamento.inscricao.model.entity.Inscricao;
import br.senai.sc.engajamento.inscricao.repository.InscricaoRepository;
import br.senai.sc.engajamento.usuario.model.entity.Usuario;
import br.senai.sc.engajamento.usuario.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InscricaoService {
    private final InscricaoRepository repository;
    private final UsuarioService usuarioService;

    public void criar(CriarInscricaoCommand cmd) {
//        try {
        Usuario usuario = usuarioService.retornaUsuario(cmd.getIdUsuario());
        Usuario canal = usuarioService.retornaUsuario(cmd.getIdCanal());
        Inscricao inscricao = repository.findByIdUsuarioAndIdCanal(usuario, canal);
        repository.deleteByIdUsuarioAndIdCanal(usuario, canal);
//        } catch (Exception e) {
//            Inscricao inscricao = new Inscricao();
//            BeanUtils.copyProperties(cmd, inscricao);
//            repository.save(inscricao);
//        }
    }

    public Inscricao buscarUm(BuscarUmInscricaoCommand cmd) {
        Usuario usuario = usuarioService.retornaUsuario(cmd.getIdUsuario());
        Usuario canal = usuarioService.retornaUsuario(cmd.getIdCanal());
        return repository.findByIdUsuarioAndIdCanal(usuario, canal);
    }
}
