package com.jgg.controlloEpidemia.model;

import lombok.*;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.Column;
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
@SelectBeforeUpdate
public class Ruolo {

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

}
