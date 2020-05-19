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
public class Provincia {

    @NotNull
    @Getter
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Comune> comuni = new LinkedList<>();
    @NotNull
    @Getter
    @OneToMany
    final private List<DecessiAnnuali> decessiAnnuali = new ArrayList<>();
    @Id
    @Getter
    @NotNull
    @GeneratedValue(generator = "increment")
    private Integer id;
    @NotNull
    @Getter
    @Setter
    private String nome;
    @NotNull
    @Getter
    @Setter
    private Integer superficie;
    @NotNull
    @Getter
    @Setter
    @OneToOne
    private Comune capoluogo;
}
