package com.jgg.controlloEpidemia.model;

import lombok.*;
import org.hibernate.annotations.SelectBeforeUpdate;

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
@SelectBeforeUpdate
public class Comune {

    @Getter
    @NotNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "comune", cascade = CascadeType.REMOVE)
    final private List<MalattieSettimanali> malattieSettimanali = new ArrayList<>();
    @Id
    @Getter
    @NotNull
    private Integer codiceIstat;
    @Getter
    @Setter
    @NotNull
    @NonNull
    private String nome;
    @Getter
    @Setter
    @NotNull
    @NonNull
    private Date dataIstituzione;
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
    private Boolean siAffacciaSulMare;
    @Getter
    @Setter
    @NotNull
    @NonNull
    @ManyToOne
    private TipoTerritorio tipoTerritorio;

}
