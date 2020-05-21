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
public class DecessiAnnuali {

    @Id
    @Getter
    @NotNull
    @EqualsAndHashCode.Include
    @GeneratedValue(generator = "increment")
    private Integer id;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    private Integer anno;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    private Integer incidentiStradali;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    private Integer malattieTumorali;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    private Integer malattieCardiovascolari;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    private Integer malattieContagiose;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    @ManyToOne
    private Provincia provincia;

}
