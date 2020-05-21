package com.jgg.controlloEpidemia.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Comune {

    @Id
    @Getter
    @Setter
    @NotNull
    private String codiceIstat;
    @NotNull
    @Getter
    @Setter
    private String nome;
    @NotNull
    @Getter
    @Setter
    private Date dataIstituzione;
    @NotNull
    @Getter
    @Setter
    private Integer superficie;
    @NotNull
    @Getter
    @Setter
    private Boolean siAffacciaSulMare;
    @NotNull
    @Getter
    @Setter
    @ManyToOne
    private TipoTerritorio tipoTerritorio;
    @Getter
    @OneToMany
    final private List<MalattieSettimanali> malattieSettimanali = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comune comune = (Comune) o;
        return getCodiceIstat().equals(comune.getCodiceIstat()) &&
                getNome().equals(comune.getNome()) &&
                getDataIstituzione().equals(comune.getDataIstituzione()) &&
                getSuperficie().equals(comune.getSuperficie()) &&
                getSiAffacciaSulMare().equals(comune.getSiAffacciaSulMare()) &&
                getTipoTerritorio().equals(comune.getTipoTerritorio());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCodiceIstat(), getNome(), getDataIstituzione(), getSuperficie(), getSiAffacciaSulMare(), getTipoTerritorio());
    }
}
