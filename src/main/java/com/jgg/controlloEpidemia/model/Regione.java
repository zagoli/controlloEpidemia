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
public class Regione {

    @Id
    @Getter
    @Setter
    @NotNull
    @GeneratedValue(generator = "increment")
    private Integer id;

    @NotNull
    @Getter
    @Setter
    private String nome;

    @NotNull
    @Getter
    @Setter
    private Integer superficie;
}