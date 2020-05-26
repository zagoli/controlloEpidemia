package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.App;
import com.jgg.controlloEpidemia.model.Provincia;
import com.jgg.controlloEpidemia.model.Regione;
import com.jgg.controlloEpidemia.service.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class etlProvincia {

    static final ComuneService comuneService = new ComuneService();
    static final ProvinciaService provinciaService = new ProvinciaService();


    public void etl(String path) throws FileNotFoundException {


        File fProvince = new File(path);
        Scanner myReader = new Scanner(fProvince);

        while (myReader.hasNextLine()) {

            //Leggo la riga, la metto in un vettore
            String riga = myReader.nextLine();
            String[] vettore;


            if (!riga.equals("")) {
                vettore = riga.split(";");

                if (vettore.length == 3) {
                    //Chiamo metodo per caricare su db la riga
                    caricaProvincia(vettore);
                }
            }
        }

        myReader.close();
    }

    private static void caricaProvincia(String[] vett) {

        if (vett[0].equals("")){
            System.out.println("Err");
        }
        if (Integer.parseInt(vett[1])<=0){
            System.out.println("Err");
        }
        if (Integer.parseInt(vett[2])<=0){
            System.out.println("Err");
        }


        //Cerco
        RuoloService rs = new RuoloService();
        if(App.utenteCorrente.getRuolo().equals(rs.findById(1))){
            Provincia p = new Provincia(vett[0],Integer.parseInt(vett[1]),comuneService.findByCodiceIstat(Integer.parseInt(vett[2])));
            provinciaService.save(p);
        }
        else{
            System.out.println("No");
        }


    }

}
