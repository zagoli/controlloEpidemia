package com.jgg.controlloEpidemia.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Comune {

    @Id
    @Getter
    @Setter
    @NotNull
    //@GeneratedValue(generator = "increment")
    private Integer codiceIstat;

    @NotNull
    @Getter
    @Setter
    private String nome;

    @NotNull
    @Getter
    @Setter
    private Date dataIstituzione;

    @NotNull
    @Getter
    @Setter
    private Integer superficie;

    @NotNull
    @Getter
    @Setter
    private Enum tipoTerritorio;

    @NotNull
    @Getter
    @Setter
    private Boolean siAffacciaSulMare;
}
