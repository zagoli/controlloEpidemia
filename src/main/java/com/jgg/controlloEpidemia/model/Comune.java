package com.jgg.controlloEpidemia.model;

import lombok.*;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.Min;
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

    @Getter
    @NotNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "comune", cascade = CascadeType.ALL)
    final private List<MalattieSettimanali> malattieSettimanali = new ArrayList<>();

    @Id
    @Getter
    @NotNull
    @Column(length = 6)
    private String codiceIstat;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @Column(unique = true)
    private String nome;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @Min(0)
    private Integer superficie;

    @Getter
    @Setter
    @NotNull
    @NonNull
    private Date dataIstituzione;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @Type(type = "true_false")
    private Boolean siAffacciaSulMare;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @ManyToOne
    private TipoTerritorio tipoTerritorio;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @ManyToOne
    private Provincia provincia;

}
