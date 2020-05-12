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
public class DecessiAnnuali {

    @Id
    @Getter
    @Setter
    @NotNull
    @GeneratedValue(generator = "increment")
    private Integer id;

    @Getter
    @Setter
    @NotNull
    private Integer anno;

    @Getter
    @Setter
    @NotNull
    private Integer incidentiStradali;

    @Getter
    @Setter
    @NotNull
    private Integer malattieTumorali;

    @Getter
    @Setter
    @NotNull
    private Integer malattieCardiovascolari;

    @Getter
    @Setter
    @NotNull
    private Integer malattieContagiose;

}
