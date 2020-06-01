package com.jgg.controlloEpidemia.model;

import lombok.*;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
@SelectBeforeUpdate
public class DecessiAnnuali {

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
    private Integer incidentiStradali;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @Min(0)
    private Integer malattieTumorali;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @Min(0)
    private Integer malattieCardiovascolari;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @Min(0)
    private Integer malattieContagiose;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @ManyToOne
    private Provincia provincia;

}
