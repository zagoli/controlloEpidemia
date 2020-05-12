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
public class MalattieSettimanali {

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
    private Integer settimana;

    @Getter
    @Setter
    @NotNull
    private Integer ricoveratiInfluenza;

    @Getter
    @Setter
    @NotNull
    private Integer inCuraInfluenza;

    @Getter
    @Setter
    @NotNull
    private Integer complicanzeRespiratorie;

    @Getter
    @Setter
    @NotNull
    private Integer ricoveratiPolmonite;

    @Getter
    @Setter
    @NotNull
    private Integer inCuraPolmonite;

    @Getter
    @Setter
    @NotNull
    private Integer ricoveratiMeningite;

    @Getter
    @Setter
    @NotNull
    private Integer inCuraMeningite;

    @Getter
    @Setter
    @NotNull
    private Integer ricoveratiEpatite;

    @Getter
    @Setter
    @NotNull
    private Integer inCuraEpatite;

    @Getter
    @Setter
    @NotNull
    private Integer ricoveratiMorbillo;

    @Getter
    @Setter
    @NotNull
    private Integer inCuraMorbillo;

    @Getter
    @Setter
    @NotNull
    private Integer ricoveratiTubercolosi;

    @Getter
    @Setter
    @NotNull
    private Integer inCuraTubercolosi;

    @Getter
    @Setter
    @NotNull
    private Integer ricoveratiGastroEnterite;

    @Getter
    @Setter
    @NotNull
    private Integer inCuraGastroEnterite;

}
