package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.model.Provincia;
import com.jgg.controlloEpidemia.model.Regione;
import com.jgg.controlloEpidemia.service.ProvinciaService;
import com.jgg.controlloEpidemia.service.RegioneService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class EtlProvincia {

    final List<Regione> regioneList = new RegioneService().findAll();
    final List<Provincia> provinciaList = new ArrayList<>();
    Regione eRegione = null;
    boolean errori;

    public int[] load(String path) {
        int righeNonLette = 0;
        int righeConErrore = 0;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File(path), StandardCharsets.UTF_8));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        String riga = null;
        try {
            assert reader != null;
            riga = reader.readLine();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        String[] vettore;
        while (riga != null) {
            errori = false;
            vettore = riga.split(";");
            if (vettore.length == 5) {
                //ID
                if (vettore[0].equals("") || !vettore[0].matches("[0-9]+")) {
                    errori = true;
                } else if (vettore[0].matches("[0-9]+") && Integer.parseInt(vettore[0]) < 1) {
                    errori = true;
                }

                //Nome
                if ((vettore[1].equals("") || !vettore[1].matches("[a-zA-Z]+(\\s+[a-zA-Z]+)*")) && !errori) {
                    errori = true;
                }
                //Superficie
                if ((Integer.parseInt(vettore[2]) < 1 || vettore[2].equals("") || !vettore[2].matches("[0-9]+")) && !errori) {
                    errori = true;
                }
                //Codice istat
                if ((vettore[3].length() != 6 || !vettore[3].matches("[0-9]+")) && !errori) {
                    errori = true;
                }
                //Regione
                if (!errori) {
                    for (Regione regione : regioneList) {
                        eRegione = null;
                        if (regione.getId().equals(Integer.parseInt(vettore[4]))) {
                            eRegione = regione;
                            break;
                        }
                    }
                    if (eRegione == null) {
                        errori = true;
                    }
                }
                //Aggiungo provincia se non ha errori
                if (!errori) {
                    provinciaList.add(new Provincia(Integer.parseInt(vettore[0]), vettore[1], Integer.parseInt(vettore[2]), vettore[3], eRegione));
                } else {
                    righeConErrore++;
                }
            } else {
                righeNonLette++;
            }

            try {
                riga = reader.readLine();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        new ProvinciaService().saveOrUpdate(provinciaList);
        try {
            reader.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return new int[]{righeConErrore, righeNonLette};
    }

}
