package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.model.Comune;
import com.jgg.controlloEpidemia.service.ComuneService;
import com.jgg.controlloEpidemia.service.ProvinciaService;
import com.jgg.controlloEpidemia.service.RuoloService;
import com.jgg.controlloEpidemia.service.TipoTerritorioService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EtlComune {

    final ComuneService comuneService = new ComuneService();
    final TipoTerritorioService tipoTerritorioService = new TipoTerritorioService();
    final RuoloService ruoloService = new RuoloService();
    final ProvinciaService provinciaService = new ProvinciaService();

    private void caricaComune(String[] vett) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date data = null;
        try {
            data = sdf.parse(vett[3]);
        } catch (ParseException e) {
            System.out.println("Errore");
        }
        boolean siAffacciaSulMare = false;
        if (Integer.parseInt(vett[4]) == 1) {
            siAffacciaSulMare = true;
        }

        //    if (App.utenteCorrente.getRuolo().equals(ruoloService.findById(1))) {
        Comune c = new Comune(Integer.parseInt(vett[0]), vett[1], Integer.parseInt(vett[2]), data, siAffacciaSulMare, tipoTerritorioService.findById(Integer.parseInt(vett[4])), provinciaService.findById(Integer.parseInt(vett[5])));
        comuneService.save(c);
        //     } else {
        //         System.out.println("No");
        //     }
    }

    public void load() throws IOException {
        File fileComuni = new File(getClass().getResource("/csvToLoad/comune.csv").getFile());
        BufferedReader reader = new BufferedReader(new FileReader(fileComuni));
        String riga = reader.readLine();
        String[] vettore;
        while (!riga.equals("") && riga != null) {
            vettore = riga.split(";");
            if (vettore.length == 7) {
                caricaComune(vettore);
            }
        }
        reader.close();
    }
}
