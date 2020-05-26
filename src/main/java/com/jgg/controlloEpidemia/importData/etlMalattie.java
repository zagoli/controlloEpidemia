package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.model.MalattieSettimanali;
import com.jgg.controlloEpidemia.service.ComuneService;
import com.jgg.controlloEpidemia.service.MalattieSettimanaliService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class etlMalattie {

    static final ComuneService comuneService = new ComuneService();
    static final MalattieSettimanaliService malattieSettimanaliService = new MalattieSettimanaliService();

    private static void caricaMalattia(String[] vett) {

        for (int i = 1; i < vett.length - 1; i++) {
            //Controllo anno
            if (Integer.parseInt(vett[0]) < 1900) {
                System.out.println("Err");
            }
            //Controllo se dato<0
            if (Integer.parseInt(vett[i]) <= 0) {
                System.out.println("Err");
            }
        }

        //QUI autenticazione per vedere se può caricare la riga o meno

        MalattieSettimanali ms = new MalattieSettimanali(Integer.parseInt(vett[0]), Integer.parseInt(vett[1]), Integer.parseInt(vett[2]), Integer.parseInt(vett[3]), Integer.parseInt(vett[4]), Integer.parseInt(vett[5]), Integer.parseInt(vett[6]), Integer.parseInt(vett[7]), Integer.parseInt(vett[8]), Integer.parseInt(vett[9]), Integer.parseInt(vett[10]), Integer.parseInt(vett[11]), Integer.parseInt(vett[12]), Integer.parseInt(vett[13]), Integer.parseInt(vett[14]), Integer.parseInt(vett[15]), Integer.parseInt(vett[16]), comuneService.findByCodiceIstat(Integer.parseInt(vett[17])));
        malattieSettimanaliService.save(ms);
    }

    public void etl(String path) throws FileNotFoundException {
        File fMalattie = new File(path);
        Scanner myReader = new Scanner(fMalattie);
        while (myReader.hasNextLine()) {
            //Leggo la riga, la metto in un vettore
            String riga = myReader.nextLine();
            String[] vettore;
            if (!riga.equals("")) {
                vettore = riga.split(";");
                if (vettore.length == 18) {
                    //Chiamo metodo per caricare su db la riga
                    caricaMalattia(vettore);
                }
            }
        }
        myReader.close();
        System.out.println(comuneService.findByCodiceIstat(1).getCodiceIstat());
    }

}
