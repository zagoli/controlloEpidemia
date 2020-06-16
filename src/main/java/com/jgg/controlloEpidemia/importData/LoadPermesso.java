package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.model.Permesso;
import com.jgg.controlloEpidemia.service.PermessoService;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LoadPermesso {

    final PermessoService permessoService = new PermessoService();

    public void load() {
        Permesso p = new Permesso("vediDecessi", "Visualizzazione dei dati annuali sui decessi");

        permessoService.saveOrUpdate(p);
    }

}
