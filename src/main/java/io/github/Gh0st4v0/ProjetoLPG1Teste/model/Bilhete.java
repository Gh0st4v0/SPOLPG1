package io.github.Gh0st4v0.ProjetoLPG1Teste.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "bilhetes")
@Getter
@Setter
@ToString
public class Bilhete {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "CHAR(36)", unique = true, nullable = false)
    private String id;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "rifa_id")
    private Rifa rifa;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
