package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.App;
import com.jgg.controlloEpidemia.model.Provincia;
import com.jgg.controlloEpidemia.service.ProvinciaService;
import com.jgg.controlloEpidemia.service.RegioneService;
import com.jgg.controlloEpidemia.service.RuoloService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class EtlProvincia {

    final ProvinciaService provinciaService = new ProvinciaService();
    final RegioneService regioneService = new RegioneService();

    private void caricaProvincia(String[] vettore) {
        if (App.utenteCorrente.getRuolo().getId() == 1) {
            Provincia p = new Provincia(Integer.parseInt(vettore[0]), vettore[1], Integer.parseInt(vettore[2]), Integer.parseInt(vettore[3]), regioneService.findById(Integer.parseInt(vettore[4])));
            provinciaService.saveOrUpdate(p);
        } else {
            System.out.println("No");
        }
    }

    public void load(String path) throws IOException {
        File fileProvince = new File(path);
        BufferedReader reader = new BufferedReader(new FileReader(fileProvince));
        String riga = reader.readLine();
        String[] vettore;
        while (riga != null && !riga.equals("")) {
            vettore = riga.split(";");
            if (vettore.length == 5) {
                caricaProvincia(vettore);
            }
            riga = reader.readLine();
        }
        reader.close();
    }
}
