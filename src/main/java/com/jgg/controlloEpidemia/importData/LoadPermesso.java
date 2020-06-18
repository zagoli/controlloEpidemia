package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.model.Permesso;
import com.jgg.controlloEpidemia.service.PermessoService;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class LoadPermesso {

    List<Permesso> permessoList = new PermessoService().findAll();

    public void load() {
        permessoList.add(new Permesso("vediDecessi", "Visualizzazione dei dati annuali sui decessi"));

        new PermessoService().saveOrUpdate(permessoList);
    }

}
