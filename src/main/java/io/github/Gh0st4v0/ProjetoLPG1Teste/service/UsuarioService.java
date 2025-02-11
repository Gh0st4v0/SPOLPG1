package io.github.Gh0st4v0.ProjetoLPG1Teste.service;

import io.github.Gh0st4v0.ProjetoLPG1Teste.DTOs.RegisterDTO;
import io.github.Gh0st4v0.ProjetoLPG1Teste.DTOs.RifaDTO;
import io.github.Gh0st4v0.ProjetoLPG1Teste.DTOs.UsuarioDTO;
import io.github.Gh0st4v0.ProjetoLPG1Teste.exceptions.*;
import io.github.Gh0st4v0.ProjetoLPG1Teste.model.Bilhete;
import io.github.Gh0st4v0.ProjetoLPG1Teste.model.Rifa;
import io.github.Gh0st4v0.ProjetoLPG1Teste.model.Usuario;
import io.github.Gh0st4v0.ProjetoLPG1Teste.repository.BilheteRepository;
import io.github.Gh0st4v0.ProjetoLPG1Teste.repository.RifaRepository;
import io.github.Gh0st4v0.ProjetoLPG1Teste.repository.UsuarioRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;
    private final RifaRepository rifaRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final BilheteRepository bilheteRepository;

    public UsuarioService(UsuarioRepository repository, RifaRepository rifaRepository, BilheteRepository bilheteRepository) {
        this.repository = repository;
        this.rifaRepository = rifaRepository;
        this.bilheteRepository = bilheteRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<UsuarioDTO> getAllUsers() {
        return repository.findAll()
                .stream()
                .map(user -> new UsuarioDTO(user.getId(), user.getNome(), user.getEmail()))
                .toList();

    }

    public List<RifaDTO> obterRifasCriadas(String id) {
        Usuario usuario = repository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuario não encontrado"));
        return usuario.getRifasCriadas()
                .stream()
                .map(rifa -> new RifaDTO(rifa.getId(), rifa.getNome(), rifa.getDescricao(), rifa.getCriador().getNome(), rifa.getDataSorteio()))
                .toList();
    }

    public List<RifaDTO> obterRifasParticipando(String id) {
        Usuario usuario = repository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuario não encontrado"));
        return usuario.getRifasQueParticipa()
                .stream()
                .map(rifa -> new RifaDTO(rifa.getId(), rifa.getNome(), rifa.getDescricao(), rifa.getCriador().getNome(), rifa.getDataSorteio()))
                .toList();
    }

    public UsuarioDTO criarUsuario(RegisterDTO usuario){
        if (repository.findByEmail(usuario.email()).isPresent()) throw new DatabaseOperationException("O email já esta cadastrado");
        String senhaCriptografada = new BCryptPasswordEncoder().encode(usuario.senha());
        Usuario novoUsuario = new Usuario(usuario.nome(), usuario.email(), senhaCriptografada);
        repository.save(novoUsuario);
        return new UsuarioDTO(novoUsuario.getId(), novoUsuario.getNome(), novoUsuario.getEmail());
    }

    public UsuarioDTO alterarNome(String id, String nome) {
        if (nome == null) {
            throw new InvalidInputException("O campo nome está vazio");
        }
        Usuario teste = repository.findById(id).orElseThrow(() -> new UserNotFoundException("Id não encontrado"));
        teste.setNome(nome);
        Usuario usuarioAlterado = repository.save(teste);
        UsuarioDTO usuarioDTO = new UsuarioDTO(usuarioAlterado.getId(), usuarioAlterado.getNome(), usuarioAlterado.getEmail());
        return usuarioDTO;

    }

    public void deletarUsuario(String id, String senha) {
        Usuario teste = repository.findById(id).orElseThrow(() -> new UserNotFoundException("Id não encontrado"));
        boolean senhaCorreta = passwordEncoder.matches(senha, teste.getSenha());
        if (!senhaCorreta) {
            throw new AuthenticationException("Senha incorreta");
        }
        repository.delete(teste);
    }

    public RifaDTO criarRifa(String nome, String descricao, Date dataSorteio, String id){
        Usuario criador = repository.findById(id).orElseThrow(() -> new UserNotFoundException("O usuario recebido para criar a rifa não existe"));
        Rifa rifa = new Rifa(nome, criador, descricao, dataSorteio);
        Rifa novaRifa = rifaRepository.save(rifa);
        return new RifaDTO(novaRifa.getId(), novaRifa.getNome(), novaRifa.getDescricao(), novaRifa.getCriador().getNome(), novaRifa.getDataSorteio());
    }

    public void comprarBilhetes(String id, String rifaId, int bilhetes) {
        if (bilhetes < 1) throw new ArithmeticException("O usuário deve comprar ao menos um bilhete");
        Usuario usuario = repository.findById(id).orElseThrow(() -> new UserNotFoundException("Usuario não encontrado"));
        Rifa rifa = rifaRepository.findById(rifaId).orElseThrow(() -> new RifaNotFoundException("Rifa não encontrada"));
        rifa.adicionarParticipante(usuario);
        for (int i = 0; i < bilhetes; i++) {
            Bilhete bilhete = new Bilhete(usuario, rifa);
            bilheteRepository.save(bilhete);
        }
        rifaRepository.save(rifa);
    }
}

