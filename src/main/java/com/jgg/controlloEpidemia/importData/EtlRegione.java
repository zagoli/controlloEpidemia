package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.App;
import com.jgg.controlloEpidemia.model.Regione;
import com.jgg.controlloEpidemia.service.ComuneService;
import com.jgg.controlloEpidemia.service.RegioneService;
import com.jgg.controlloEpidemia.service.RuoloService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class EtlRegione {

    static final ComuneService comuneService = new ComuneService();
    static final RegioneService regioneService = new RegioneService();
    static final RuoloService ruoloService = new RuoloService();

    private static void caricaRegione(String[] vett) {
        if (App.utenteCorrente.getRuolo().equals(ruoloService.findById(1))) {
            Regione regione = new Regione(vett[0], Integer.parseInt(vett[1]), comuneService.findByCodiceIstat(Integer.parseInt(vett[2])));
            regioneService.save(regione);
        } else {
            System.out.println("No");
        }
    }

    public void load(String path) throws IOException {
        File fileRegioni = new File(path);
        BufferedReader reader = new BufferedReader(new FileReader(fileRegioni));
        String riga = reader.readLine();
        String[] vettore;
        while (!riga.equals("") && riga != null) {
            vettore = riga.split(";");
            if (vettore.length == 3) {
                caricaRegione(vettore);
            }
        }
        reader.close();
    }

}
