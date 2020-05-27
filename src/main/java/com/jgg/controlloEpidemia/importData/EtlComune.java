package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.App;
import com.jgg.controlloEpidemia.model.Comune;
import com.jgg.controlloEpidemia.service.ComuneService;
import com.jgg.controlloEpidemia.service.RuoloService;
import com.jgg.controlloEpidemia.service.TipoTerritorioService;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class EtlComune {

    static final ComuneService comuneService = new ComuneService();
    static final TipoTerritorioService tipoTerritorioService = new TipoTerritorioService();
    static final RuoloService ruoloService = new RuoloService();

    private static void caricaComune(String[] vett) {
        if (vett[0].equals("")) {
            System.out.println("Errore");
        }
        if (Integer.parseInt(vett[2]) <= 0) {
            System.out.println("Errore");
        }
        if (!vett[3].equals("si") && !vett[3].equals("no")) {
            System.out.println("Errore");
        }
        if (Integer.parseInt(vett[4]) <= 0) {
            System.out.println("Errore");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date data = null;
        try {
            data = sdf.parse(vett[1]);
        } catch (ParseException e) {
            System.out.println("Errore");
        }
        boolean siAffacciaSulMare = false;
        if (Integer.parseInt(vett[3]) == 1) {
            siAffacciaSulMare = true;
        }
        if (App.utenteCorrente.getRuolo().equals(ruoloService.findById(1))) {
            Comune c = new Comune(vett[0], data, Integer.parseInt(vett[2]), siAffacciaSulMare, tipoTerritorioService.findById(Integer.parseInt(vett[4])));
            comuneService.save(c);
        } else {
            System.out.println("No");
        }
    }

    public void etl(String path) throws FileNotFoundException {
        File fileComune = new File(path);
        Scanner reader = new Scanner(fileComune);
        while (reader.hasNextLine()) {
            String riga = reader.nextLine();
            String[] vettore;
            if (!riga.equals("")) {
                vettore = riga.split(";");
                if (vettore.length == 5) {
                    caricaComune(vettore);
                }
            }
        }
        reader.close();
    }
}
