package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.App;
import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import com.jgg.controlloEpidemia.service.DecessiAnnualiService;
import com.jgg.controlloEpidemia.service.ProvinciaService;
import com.jgg.controlloEpidemia.service.RuoloService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class EtlDecessi {

    static final DecessiAnnualiService decessiAnnualiService = new DecessiAnnualiService();
    static final ProvinciaService provinciaService = new ProvinciaService();
    static final RuoloService ruoloService = new RuoloService();

    private static void caricaDecessi(String[] vett) {
        if (Integer.parseInt(vett[0]) < 1900) {
            System.out.println("Errore");
        }
        if (Integer.parseInt(vett[1]) <= 0) {
            System.out.println("Errore");
        }
        if (Integer.parseInt(vett[2]) <= 0) {
            System.out.println("Errore");
        }
        if (Integer.parseInt(vett[3]) <= 0) {
            System.out.println("Errore");
        }
        if (Integer.parseInt(vett[4]) <= 0) {
            System.out.println("Errore");
        }
        if (Integer.parseInt(vett[5]) < 0) {
            System.out.println("Errore");
        }
        if (App.utenteCorrente.getRuolo().equals(ruoloService.findById(1))) {
            DecessiAnnuali da = new DecessiAnnuali(Integer.parseInt(vett[0]), Integer.parseInt(vett[1]), Integer.parseInt(vett[2]), Integer.parseInt(vett[3]), Integer.parseInt(vett[4]), provinciaService.findById(Integer.parseInt(vett[5])));
            decessiAnnualiService.save(da);
        } else {
            System.out.println("No");
        }
    }

    public void load(String path) throws IOException {
        File fileDecessi = new File(path);
        BufferedReader reader = new BufferedReader(new FileReader(fileDecessi));
        String riga = reader.readLine();
        String[] vettore;
        while (!riga.equals("") && riga != null) {
            vettore = riga.split(";");
            if (vettore.length == 6) {
                caricaDecessi(vettore);
            }
        }
        reader.close();
    }
}
