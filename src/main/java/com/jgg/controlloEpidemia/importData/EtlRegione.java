package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.App;
import com.jgg.controlloEpidemia.model.Regione;
import com.jgg.controlloEpidemia.service.ComuneService;
import com.jgg.controlloEpidemia.service.RegioneService;
import com.jgg.controlloEpidemia.service.RuoloService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EtlRegione {

    static final ComuneService comuneService = new ComuneService();
    static final RegioneService regioneService = new RegioneService();

    private static void caricaRegione(String[] vett) {

        if (vett[0].equals("")) {
            System.out.println("Err");
        }
        if (Integer.parseInt(vett[1]) <= 0) {
            System.out.println("Err");
        }
        if (Integer.parseInt(vett[2]) <= 0) {
            System.out.println("Err");
        }


        //Cerco
        RuoloService rs = new RuoloService();
        if (App.utenteCorrente.getRuolo().equals(rs.findById(1))) {
            Regione r = new Regione(vett[0], Integer.parseInt(vett[1]), comuneService.findByCodiceIstat(Integer.parseInt(vett[2])));
            regioneService.save(r);
        } else {
            System.out.println("No");
        }


    }

    public void etl(String path) throws FileNotFoundException {


        File fRegioni = new File(path);
        Scanner myReader = new Scanner(fRegioni);

        while (myReader.hasNextLine()) {

            //Leggo la riga, la metto in un vettore
            String riga = myReader.nextLine();
            String[] vettore;


            if (!riga.equals("")) {
                vettore = riga.split(";");

                if (vettore.length == 3) {
                    //Chiamo metodo per caricare su db la riga
                    caricaRegione(vettore);
                }
            }
        }

        myReader.close();
    }

}
