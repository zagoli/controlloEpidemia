package com.jgg.controlloEpidemia.model;

import lombok.*;

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
public class MalattieSettimanali {

    @Id
    @Getter
    @NotNull
    @GeneratedValue(generator = "increment")
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer id;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer anno;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer settimana;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer ricoveratiInfluenza;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer inCuraInfluenza;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer complicanzeRespiratorie;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer ricoveratiPolmonite;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer inCuraPolmonite;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer ricoveratiMeningite;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer inCuraMeningite;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer ricoveratiEpatite;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer inCuraEpatite;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer ricoveratiMorbillo;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer inCuraMorbillo;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer ricoveratiTubercolosi;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer inCuraTubercolosi;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer ricoveratiGastroenterite;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer inCuraGastroenterite;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    @ToString.Include
    @ManyToOne
    private Comune comune;

}
