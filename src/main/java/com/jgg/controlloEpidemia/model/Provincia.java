package com.jgg.controlloEpidemia.model;

import lombok.*;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import javax.validation.constraints.Min;
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

    @Getter
    @NotNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.REMOVE)
    private final List<Comune> comuni = new LinkedList<>();
    @Getter
    @NotNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.REMOVE)
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
