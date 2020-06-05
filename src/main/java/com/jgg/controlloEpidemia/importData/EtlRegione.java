package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.App;
import com.jgg.controlloEpidemia.model.Regione;
import com.jgg.controlloEpidemia.service.RegioneService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class EtlRegione {

    final RegioneService regioneService = new RegioneService();

    private void caricaRegione(String[] vettore) {
        if (App.utenteCorrente.getRuolo().getId() == 1) {
            Regione r = new Regione(vettore[0], Integer.parseInt(vettore[1]), Integer.parseInt(vettore[2]));
            regioneService.saveOrUpdate(r);
        } else {
            System.out.println("No");
        }
    }

    public void load(String path) throws IOException {
        File fileRegioni = new File(path);
        BufferedReader reader = new BufferedReader(new FileReader(fileRegioni));
        String riga = reader.readLine();
        String[] vettore;
        while (riga != null && !riga.equals("")) {
            vettore = riga.split(";");
            if (vettore.length == 3) {
                caricaRegione(vettore);
            }
            riga = reader.readLine();
        }
        reader.close();
    }

}
