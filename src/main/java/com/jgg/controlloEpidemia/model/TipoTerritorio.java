package com.jgg.controlloEpidemia.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode
public class TipoTerritorio {

    @Id
    @Getter
    @NotNull
    @GeneratedValue(generator = "increment")
    @EqualsAndHashCode.Include
    private Integer id;

    @Getter
    @Setter
    @NotNull
    @NonNull
    @EqualsAndHashCode.Include
    private String nome;
}
