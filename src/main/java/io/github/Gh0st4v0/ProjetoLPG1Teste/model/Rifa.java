package io.github.Gh0st4v0.ProjetoLPG1Teste.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "rifas")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Rifa {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "CHAR(36)", unique = true, nullable = false)
    private String id;

    private String nome;

    @Column(name = "data_criacao")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataCriacao;

    @Column(name = "data_sorteio")
    private Date dataSorteio;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "vencedor_id")
    private Usuario vencedor;

    private String descricao;

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario criador;

    @JsonManagedReference
    @OneToMany(mappedBy = "rifa")
    private Set<Bilhete> bilhetes = new HashSet<>();

    @JsonManagedReference
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @ManyToMany
    @JoinTable(
            name = "usuarios_rifas",
            joinColumns = @JoinColumn(name = "rifa_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private Set<Usuario> participantes = new HashSet<>();

    public Rifa(String nome, Usuario criador, String descricao, Date dataSorteio){
        this.nome = nome;
        this.criador = criador;
        this.descricao = descricao;
        this.dataSorteio = dataSorteio;
    }

    public void adicionarParticipante(Usuario usuario){
        participantes.add(usuario);
    }


}
