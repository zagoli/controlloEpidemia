package com.jgg.controlloEpidemia.model;

import lombok.*;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode

public class Regione {

    @Getter
    @NotNull
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(cascade = CascadeType.REMOVE)
    private final List<Provincia> province = new LinkedList<>();
    @Id
    @Getter
    @NotNull
    @GeneratedValue(generator = "increment")
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
}