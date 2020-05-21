package com.jgg.controlloEpidemia.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
public class Comune {

    @Id
    @Getter
    @NotNull
    @EqualsAndHashCode.Include
    @ToString.Include
    private String codiceIstat;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    @ToString.Include
    private String nome;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    @ToString.Include
    private Date dataIstituzione;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer superficie;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    @ToString.Include
    private Boolean siAffacciaSulMare;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    @ToString.Include
    @ManyToOne
    private TipoTerritorio tipoTerritorio;

    @Getter
    @NotNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "comune", cascade = CascadeType.REMOVE)
    final private List<MalattieSettimanali> malattieSettimanali = new ArrayList<>();

}
