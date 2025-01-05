package io.github.Gh0st4v0.ProjetoLPG1Teste.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "rifas")
@Getter
@Setter
@ToString
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

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario criador;

    @JsonManagedReference
    @OneToMany(mappedBy = "rifa_id")
    private List<Bilhete> bilhetes;

    @JsonManagedReference
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @ManyToMany
    @JoinTable(
            name = "usuarios_rifas",
            joinColumns = @JoinColumn(name = "rifa_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id")
    )
    private List<Usuario> participantes;
}
