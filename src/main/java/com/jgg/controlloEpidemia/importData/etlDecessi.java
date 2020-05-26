package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.App;
import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import com.jgg.controlloEpidemia.model.Provincia;
import com.jgg.controlloEpidemia.service.ComuneService;
import com.jgg.controlloEpidemia.service.DecessiAnnualiService;
import com.jgg.controlloEpidemia.service.ProvinciaService;
import com.jgg.controlloEpidemia.service.RuoloService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class etlDecessi {

    static final DecessiAnnualiService decessiAnnualiService = new DecessiAnnualiService();
    static final ProvinciaService provinciaService = new ProvinciaService();


    public void etl(String path) throws FileNotFoundException {


        File fDecessi = new File(path);
        Scanner myReader = new Scanner(fDecessi);

        while (myReader.hasNextLine()) {

            //Leggo la riga, la metto in un vettore
            String riga = myReader.nextLine();
            String[] vettore;


            if (!riga.equals("")) {
                vettore = riga.split(";");

                if (vettore.length == 6) {
                    //Chiamo metodo per caricare su db la riga
                    caricaDecessi(vettore);
                }
            }
        }

        myReader.close();
    }

    private static void caricaDecessi(String[] vett) {

        if (Integer.parseInt(vett[0])<1900){
            System.out.println("Err");
        }
        if (Integer.parseInt(vett[1])<=0){
            System.out.println("Err");
        }
        if (Integer.parseInt(vett[2])<=0){
            System.out.println("Err");
        }
        if (Integer.parseInt(vett[3])<=0){
            System.out.println("Err");
        }
        if (Integer.parseInt(vett[4])<=0){
            System.out.println("Err");
        }
        if (Integer.parseInt(vett[5])<0){
            System.out.println("Err");
        }


        //Cerco
        RuoloService rs = new RuoloService();
        if(App.utenteCorrente.getRuolo().equals(rs.findById(1))){
            DecessiAnnuali da = new DecessiAnnuali(Integer.parseInt(vett[0]),Integer.parseInt(vett[1]),Integer.parseInt(vett[2]),Integer.parseInt(vett[3]),Integer.parseInt(vett[4]),provinciaService.findById(Integer.parseInt(vett[5])));
            decessiAnnualiService.save(da);
        }
        else{
            System.out.println("No");
        }


    }

}
