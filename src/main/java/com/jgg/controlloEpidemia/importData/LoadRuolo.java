package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.model.Ruolo;
import com.jgg.controlloEpidemia.service.RuoloService;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class LoadRuolo {

    final List<Ruolo> ruoloList = new ArrayList<>();

    public void load() {
        ruoloList.add(new Ruolo(1, "Amministratore"));
        ruoloList.add(new Ruolo(2, "Personale a contratto"));
        ruoloList.add(new Ruolo(3, "Personale dell'ente"));
        ruoloList.add(new Ruolo(4, "Ricercatore Analista"));

        new RuoloService().saveOrUpdate(ruoloList);
    }

}
