package com.jgg.controlloEpidemia.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Utente {

    @Id
    @Getter
    @Setter
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
    private Enum ruolo;

}
