package io.github.Gh0st4v0.ProjetoLPG1Teste.service;

import io.github.Gh0st4v0.ProjetoLPG1Teste.DTOs.RegisterDTO;
import io.github.Gh0st4v0.ProjetoLPG1Teste.DTOs.RifaDTO;
import io.github.Gh0st4v0.ProjetoLPG1Teste.DTOs.UsuarioDTO;
import io.github.Gh0st4v0.ProjetoLPG1Teste.exceptions.AuthenticationException;
import io.github.Gh0st4v0.ProjetoLPG1Teste.exceptions.DatabaseOperationException;
import io.github.Gh0st4v0.ProjetoLPG1Teste.exceptions.InvalidInputException;
import io.github.Gh0st4v0.ProjetoLPG1Teste.exceptions.UserNotFoundException;
import io.github.Gh0st4v0.ProjetoLPG1Teste.model.Rifa;
import io.github.Gh0st4v0.ProjetoLPG1Teste.model.Usuario;
import io.github.Gh0st4v0.ProjetoLPG1Teste.repository.RifaRepository;
import io.github.Gh0st4v0.ProjetoLPG1Teste.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;
    private final RifaRepository rifaRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioService(UsuarioRepository repository, RifaRepository rifaRepository) {
        this.repository = repository;
        this.rifaRepository = rifaRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public List<UsuarioDTO> getAllUsers() {
        try {
            return repository.findAll()
                    .stream()
                    .map(user -> new UsuarioDTO(user.getId(), user.getNome(), user.getEmail()))
                    .toList();
        } catch (Exception e) {
            throw new DatabaseOperationException("Erro ao tentar recuperar todos os usuários");
        }
    }

    public UsuarioDTO criarUsuario(RegisterDTO usuario){
        try {
            if (repository.findByEmail(usuario.email()).isPresent()) throw new DatabaseOperationException("O email já esta cadastrado");
            String senhaCriptografada = new BCryptPasswordEncoder().encode(usuario.senha());
            Usuario novoUsuario = new Usuario(usuario.nome(), usuario.email(), senhaCriptografada);
            repository.save(novoUsuario);
            return new UsuarioDTO(novoUsuario.getId(), novoUsuario.getNome(), novoUsuario.getEmail());
        } catch (Exception e) {
            throw new DatabaseOperationException("Erro ao tentar cadastrar usuario");
        }

    }

    public UsuarioDTO alterarNome(String id, String nome) {
        if (nome == null) {
            throw new InvalidInputException("O campo nome está vazio");
        }
        Usuario teste = repository.findById(id).orElseThrow(() -> new UserNotFoundException("Id não encontrado"));
        try {
            teste.setNome(nome);
            Usuario usuarioAlterado = repository.save(teste);
            UsuarioDTO usuarioDTO = new UsuarioDTO(usuarioAlterado.getId(), usuarioAlterado.getNome(), usuarioAlterado.getEmail());
            return usuarioDTO;
        } catch (Exception e) {
            throw new DatabaseOperationException("Erro ao tentar alterar o nome do usuário");
        }
    }

    public void deletarUsuario(String id, String senha) {
        Usuario teste = repository.findById(id).orElseThrow(() -> new UserNotFoundException("Id não encontrado"));
        boolean senhaCorreta = passwordEncoder.matches(senha, teste.getSenha());
        if (!senhaCorreta) {
            throw new AuthenticationException("Senha incorreta");
        }
        try {
            repository.delete(teste);
        } catch (Exception e) {
            throw new DatabaseOperationException("Erro ao tentar deletar usuário");
        }
    }

    public RifaDTO criarRifa(String nome, String id){
        try{
            Usuario criador = repository.findById(id).orElseThrow(() -> new UserNotFoundException("O usuario recebido para criar a rifa não existe"));
            Rifa rifa = new Rifa(nome, criador);
            Rifa novaRifa = rifaRepository.save(rifa);
            return new RifaDTO(novaRifa.getId(), novaRifa.getNome(), novaRifa.getCriador().getNome());
        } catch (Exception e){
            throw new DatabaseOperationException("Erro ao tentar criar rifa");
        }

    }
}

