package com.jgg.controlloEpidemia.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
    private Integer id;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @Min(1990)
    private Integer anno;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @Min(0)
    @Max(52)
    private Integer settimana;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @Min(0)
    private Integer ricoveratiInfluenza;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @Min(0)
    private Integer inCuraInfluenza;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @Min(0)
    private Integer complicanzeRespiratorie;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @Min(0)
    private Integer ricoveratiPolmonite;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @Min(0)
    private Integer inCuraPolmonite;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @Min(0)
    private Integer ricoveratiMeningite;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @Min(0)
    private Integer inCuraMeningite;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @Min(0)
    private Integer ricoveratiEpatite;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @Min(0)
    private Integer inCuraEpatite;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @Min(0)
    private Integer ricoveratiMorbillo;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @Min(0)
    private Integer inCuraMorbillo;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @Min(0)
    private Integer ricoveratiTubercolosi;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @Min(0)
    private Integer inCuraTubercolosi;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @Min(0)
    private Integer ricoveratiGastroenterite;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @Min(0)
    private Integer inCuraGastroenterite;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @ManyToOne
    private Comune comune;

}
