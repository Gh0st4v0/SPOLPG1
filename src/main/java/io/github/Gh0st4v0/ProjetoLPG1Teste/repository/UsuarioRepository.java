package io.github.Gh0st4v0.ProjetoLPG1Teste.repository;

import io.github.Gh0st4v0.ProjetoLPG1Teste.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Optional<UserDetails> findByEmail(String email);

    @Query("SELECT u FROM Usuario u WHERE u.email = :email")
    Optional<Usuario> findEntityByEmail(String email);

    Optional<Usuario> findById(String id);
}
