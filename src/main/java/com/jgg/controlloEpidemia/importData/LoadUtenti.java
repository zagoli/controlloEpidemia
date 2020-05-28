package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.model.Utente;
import com.jgg.controlloEpidemia.service.RuoloService;
import com.jgg.controlloEpidemia.service.UtenteService;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LoadUtenti {

    static final UtenteService utenteService = new UtenteService();
    static final RuoloService ruoloService = new RuoloService();

    public void load() {
        Utente u = new Utente("Admin", "pp", "Gianni", "Bolla", ruoloService.findById(1));
        Utente u2 = new Utente("Contratto", "tt", "Costante", "Napolitani", ruoloService.findById(2));
        Utente u3 = new Utente("Personale", "ww", "Ignazio", "Romano", ruoloService.findById(3));
        Utente u4 = new Utente("Ricercatore", "gg", "Dalila", "Ferri", ruoloService.findById(4));

        utenteService.saveIfNotPresent(u);
        utenteService.saveIfNotPresent(u2);
        utenteService.saveIfNotPresent(u3);
        utenteService.saveIfNotPresent(u4);


    }
}
