package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.App;
import com.jgg.controlloEpidemia.model.Provincia;
import com.jgg.controlloEpidemia.service.ComuneService;
import com.jgg.controlloEpidemia.service.ProvinciaService;
import com.jgg.controlloEpidemia.service.RuoloService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EtlProvincia {

    static final ComuneService comuneService = new ComuneService();
    static final ProvinciaService provinciaService = new ProvinciaService();
    static final RuoloService ruoloService = new RuoloService();

    private static void caricaProvincia(String[] vett) {
        if (vett[0].equals("")) {
            System.out.println("Errore");
        }
        if (Integer.parseInt(vett[1]) <= 0) {
            System.out.println("Errore");
        }
        if (Integer.parseInt(vett[2]) <= 0) {
            System.out.println("Errore");
        }
        if (App.utenteCorrente.getRuolo().equals(ruoloService.findById(1))) {
            Provincia p = new Provincia(vett[0], Integer.parseInt(vett[1]), comuneService.findByCodiceIstat(Integer.parseInt(vett[2])));
            provinciaService.save(p);
        } else {
            System.out.println("No");
        }
    }

    public void etl(String path) throws FileNotFoundException {
        File fileProvince = new File(path);
        Scanner reader = new Scanner(fileProvince);
        while (reader.hasNextLine()) {
            String riga = reader.nextLine();
            String[] vettore;
            if (!riga.equals("")) {
                vettore = riga.split(";");
                if (vettore.length == 3) {
                    caricaProvincia(vettore);
                }
            }
        }
        reader.close();
    }
}
