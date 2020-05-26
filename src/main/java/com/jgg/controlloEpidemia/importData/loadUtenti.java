package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.model.Utente;
import com.jgg.controlloEpidemia.service.UtenteService;


public class loadUtenti {

    static final UtenteService utenteService = new UtenteService();

    public void loadTipoterritorio() {
        Utente u = new Utente();

        utenteService.saveOrUpdate(u);


    }
}
