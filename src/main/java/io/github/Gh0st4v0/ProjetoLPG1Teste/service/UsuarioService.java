package io.github.Gh0st4v0.ProjetoLPG1Teste.service;

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

    public List<Usuario> getAllUsers() {
        try {
            return repository.findAll();
        } catch (Exception e) {
            throw new DatabaseOperationException("Erro ao tentar recuperar todos os usuários");
        }
    }


    public Usuario alterarNome(String id, Usuario user) {
        if (user.getNome() == null) {
            throw new InvalidInputException("O campo nome está vazio");
        }
        Usuario teste = repository.findById(id).orElseThrow(() -> new UserNotFoundException("Id não encontrado"));
        try {
            teste.setNome(user.getNome());
            return repository.save(teste);
        } catch (Exception e) {
            throw new DatabaseOperationException("Erro ao tentar alterar o nome do usuário");
        }
    }

    public void deletarUsuario(String id, Usuario user) {
        Usuario teste = repository.findById(id).orElseThrow(() -> new UserNotFoundException("Id não encontrado"));
        boolean senhaCorreta = passwordEncoder.matches(user.getSenha(), teste.getSenha());
        if (!senhaCorreta) {
            throw new AuthenticationException("Senha incorreta");
        }
        try {
            repository.delete(teste);
        } catch (Exception e) {
            throw new DatabaseOperationException("Erro ao tentar deletar usuário");
        }
    }

    public Rifa criarRifa(Rifa rifa, String id){
        Usuario teste = repository.findById(id).orElseThrow(() -> new UserNotFoundException("O usuario recebido para criar a rifa não existe"));
        rifa.setCriador(teste);
        return rifaRepository.save(rifa);
    }
}

