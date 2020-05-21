package com.jgg.controlloEpidemia.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
public class Provincia {

    @Id
    @Getter
    @NotNull
    @GeneratedValue(generator = "increment")
    private Integer id;

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
    private Integer superficie;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    @OneToOne
    private Comune capoluogo;

    @Getter
    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Comune> comuni = new LinkedList<>();

    @Getter
    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    final private List<DecessiAnnuali> decessiAnnuali = new ArrayList<>();

}
