package com.jgg.controlloEpidemia.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode

public class Ruolo {

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

}
