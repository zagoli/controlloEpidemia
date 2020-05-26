package com.jgg.controlloEpidemia.model;

import lombok.*;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@SelectBeforeUpdate
public class MalattieSettimanali {

    @Id
    @Getter
    @NotNull
    @GeneratedValue(generator = "increment")
    private Integer id;

    @Getter
    @Setter
    @NotNull
    @NonNull
    private Integer anno;

    @Getter
    @Setter
    @NotNull
    @NonNull
    private Integer settimana;

    @Getter
    @Setter
    @NotNull
    @NonNull
    private Integer ricoveratiInfluenza;

    @Getter
    @Setter
    @NotNull
    @NonNull
    private Integer inCuraInfluenza;

    @Getter
    @Setter
    @NotNull
    @NonNull
    private Integer complicanzeRespiratorie;

    @Getter
    @Setter
    @NotNull
    @NonNull
    private Integer ricoveratiPolmonite;

    @Getter
    @Setter
    @NotNull
    @NonNull
    private Integer inCuraPolmonite;

    @Getter
    @Setter
    @NotNull
    @NonNull
    private Integer ricoveratiMeningite;

    @Getter
    @Setter
    @NotNull
    @NonNull
    private Integer inCuraMeningite;

    @Getter
    @Setter
    @NotNull
    @NonNull
    private Integer ricoveratiEpatite;

    @Getter
    @Setter
    @NotNull
    @NonNull
    private Integer inCuraEpatite;

    @Getter
    @Setter
    @NotNull
    @NonNull
    private Integer ricoveratiMorbillo;

    @Getter
    @Setter
    @NotNull
    @NonNull
    private Integer inCuraMorbillo;

    @Getter
    @Setter
    @NotNull
    @NonNull
    private Integer ricoveratiTubercolosi;

    @Getter
    @Setter
    @NotNull
    @NonNull
    private Integer inCuraTubercolosi;

    @Getter
    @Setter
    @NotNull
    @NonNull
    private Integer ricoveratiGastroenterite;

    @Getter
    @Setter
    @NotNull
    @NonNull
    private Integer inCuraGastroenterite;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @ManyToOne
    private Comune comune;

}
