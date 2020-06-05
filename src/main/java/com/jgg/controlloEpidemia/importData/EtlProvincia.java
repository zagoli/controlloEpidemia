package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.App;
import com.jgg.controlloEpidemia.model.Provincia;
import com.jgg.controlloEpidemia.service.ComuneService;
import com.jgg.controlloEpidemia.service.ProvinciaService;
import com.jgg.controlloEpidemia.service.RegioneService;
import com.jgg.controlloEpidemia.service.RuoloService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class EtlProvincia {

    final ProvinciaService provinciaService = new ProvinciaService();
    final RuoloService ruoloService = new RuoloService();
    final RegioneService regioneService = new RegioneService();

    private void caricaProvincia(String[] vett) {
        if (App.utenteCorrente.getRuolo().equals(ruoloService.findById(1))) {
            Provincia p = new Provincia(Integer.parseInt(vett[0]), vett[1], Integer.parseInt(vett[2]), vett[3], regioneService.findById(Integer.parseInt(vett[4])));
            provinciaService.save(p);
        } else {
            System.out.println("No");
        }
    }

    public void load(String path) throws IOException {
        File fileProvince = new File(path);
        BufferedReader reader = new BufferedReader(new FileReader(fileProvince));
        String riga = reader.readLine();
        String[] vettore;
        while (!riga.equals("") && riga != null) {
            vettore = riga.split(";");
            if (vettore.length == 5) {
                caricaProvincia(vettore);
            }
        }
        reader.close();
    }
}
