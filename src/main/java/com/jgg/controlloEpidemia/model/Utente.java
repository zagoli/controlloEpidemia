package com.jgg.controlloEpidemia.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Utente {

    @NotNull
    @Getter
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Permessi> permessi = new ArrayList<>();
    @NotNull
    @Getter
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Comune> comuni = new ArrayList<>();
    @Id
    @Getter
    @NotNull
    @GeneratedValue(generator = "increment")
    private Integer id;
    @NotNull
    @Getter
    @Setter
    private String username;
    @NotNull
    @Getter
    @Setter
    private String password;
    @NotNull
    @Getter
    @Setter
    private String nome;
    @NotNull
    @Getter
    @Setter
    private String cognome;
    @NotNull
    @Getter
    @Setter
    @ManyToOne
    private Ruolo ruolo;

}
