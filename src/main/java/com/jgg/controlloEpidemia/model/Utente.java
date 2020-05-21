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

    @Getter
    @NotNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.REMOVE)
    private final List<Permesso> permesso = new ArrayList<>();
    @Getter
    @NotNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.REMOVE)
    private final List<Comune> comuni = new ArrayList<>();
    @Id
    @Getter
    @NotNull
    @GeneratedValue(generator = "increment")
    private Integer id;
    @Getter
    @Setter
    @NotNull
    @NonNull
    private String username;
    @Getter
    @Setter
    @NotNull
    @NonNull
    private String password;
    @Getter
    @Setter
    @NotNull
    @NonNull
    private String nome;
    @Getter
    @Setter
    @NotNull
    @NonNull
    private String cognome;
    @Getter
    @Setter
    @NotNull
    @NonNull
    @ManyToOne
    private Ruolo ruolo;
}
