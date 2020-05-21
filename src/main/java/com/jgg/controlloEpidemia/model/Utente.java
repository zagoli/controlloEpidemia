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
@RequiredArgsConstructor
@EqualsAndHashCode
public class Utente {

    @Id
    @Getter
    @NotNull
    @GeneratedValue(generator = "increment")
    private Integer id;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    private String username;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    private String password;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    private String nome;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    private String cognome;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    @ManyToOne
    private Ruolo ruolo;

    @Getter
    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Permessi> permessi = new ArrayList<>();

    @Getter
    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Comune> comuni = new ArrayList<>();
}
