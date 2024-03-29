package com.jgg.controlloEpidemia.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode

public class Provincia {

    @Getter
    @NotNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "provincia", cascade = CascadeType.ALL)
    private final List<Comune> comuni = new ArrayList<>();

    @Getter
    @NotNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "provincia", cascade = CascadeType.ALL)
    final private List<DecessiAnnuali> decessiAnnuali = new ArrayList<>();

    @Id
    @Getter
    @NotNull
    private Integer id;

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
    private String capoluogo;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @ManyToOne
    private Regione regione;

}
