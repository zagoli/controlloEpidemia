package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.model.Permesso;
import com.jgg.controlloEpidemia.model.Ruolo;
import com.jgg.controlloEpidemia.service.PermessoService;

public class loadPermesso {

    static final PermessoService permessoService = new PermessoService();

    public void loadTipoterritorio() {
        Permesso p = new Permesso();

        //usare merge o saveOrUpdate?
        permessoService.save(p);

    }
}
