package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.model.Utente;
import com.jgg.controlloEpidemia.service.PermessoService;
import com.jgg.controlloEpidemia.service.RuoloService;
import com.jgg.controlloEpidemia.service.UtenteService;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LoadUtenti {

    final UtenteService utenteService = new UtenteService();
    final RuoloService ruoloService = new RuoloService();
    final PermessoService permessoService = new PermessoService();

    public void load() {
        Utente u = new Utente("Admin", "aa", "Gianni", "Bolla", ruoloService.findById(1));
        Utente u2 = new Utente("Contratto", "cc", "Costante", "Napolitani", ruoloService.findById(2));
        Utente u3 = new Utente("Personale", "pp", "Ignazio", "Romano", ruoloService.findById(3));
        Utente u4 = new Utente("Ricercatore", "rr", "Dalila", "Ferri", ruoloService.findById(4));
        u.getPermesso().add(permessoService.findById(1));
        u4.getPermesso().add(permessoService.findById(1));

        utenteService.saveIfNotPresent(u);
        utenteService.saveIfNotPresent(u2);
        utenteService.saveIfNotPresent(u3);
        utenteService.saveIfNotPresent(u4);

    }
}
