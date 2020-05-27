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

    private static void caricaComune(String[] vett) {

        if (vett[0].equals("")) {
            System.out.println("Err");
        }
        if (Integer.parseInt(vett[2]) <= 0) {
            System.out.println("Err");
        }
        if (!vett[3].equals("si") && !vett[3].equals("no")) {
            System.out.println("Err");
        }
        if (Integer.parseInt(vett[4]) <= 0) {
            System.out.println("Err");
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date data = null;
        try {
            data = sdf.parse(vett[1]);
        } catch (ParseException e) {
            System.out.println("Err");
        }

        boolean siAff = false;
        if (vett[3].equals("si")) {
            siAff = true;
        }

        //Cerco
        RuoloService rs = new RuoloService();
        if (App.utenteCorrente.getRuolo().equals(rs.findById(1))) {
            Comune c = new Comune(vett[0], data, Integer.parseInt(vett[2]), siAff, tipoTerritorioService.findById(Integer.parseInt(vett[4])));
            comuneService.save(c);
        } else {
            System.out.println("No");
        }


    }

    public void etl(String path) throws FileNotFoundException {


        File fComune = new File(path);
        Scanner myReader = new Scanner(fComune);

        while (myReader.hasNextLine()) {

            //Leggo la riga, la metto in un vettore
            String riga = myReader.nextLine();
            String[] vettore;


            if (!riga.equals("")) {
                vettore = riga.split(";");

                if (vettore.length == 5) {
                    //Chiamo metodo per caricare su db la riga
                    caricaComune(vettore);
                }
            }
        }

        myReader.close();
    }

}
