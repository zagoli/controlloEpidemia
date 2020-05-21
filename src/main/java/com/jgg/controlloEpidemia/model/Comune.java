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
    private String codiceIstat;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    private String nome;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    private Date dataIstituzione;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    private Integer superficie;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    private Boolean siAffacciaSulMare;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    @ManyToOne
    private TipoTerritorio tipoTerritorio;

    @Getter
    @EqualsAndHashCode.Exclude
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "comune", cascade = CascadeType.ALL)
    final private List<MalattieSettimanali> malattieSettimanali = new ArrayList<>();

}
