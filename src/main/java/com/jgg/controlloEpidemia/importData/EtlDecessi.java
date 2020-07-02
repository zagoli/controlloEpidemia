package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.model.DecessiAnnuali;
import com.jgg.controlloEpidemia.model.Provincia;
import com.jgg.controlloEpidemia.service.DecessiAnnualiService;
import com.jgg.controlloEpidemia.service.ProvinciaService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EtlDecessi {

    final List<Provincia> provinciaList = new ProvinciaService().findAll();
    final List<DecessiAnnuali> decessiAnnualiList = new ArrayList<>();
    Provincia eProvincia = null;
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
            if (vettore.length == 6) {
                //Anno
                if (vettore[0].equals("") || !vettore[0].matches("[0-9]+")) {
                    errori = true;
                } else if (vettore[0].matches("[0-9]+") && (Integer.parseInt(vettore[0]) < 1500 || Integer.parseInt(vettore[0]) > Calendar.getInstance().get(Calendar.YEAR))) {
                    errori = true;
                }

                //Dati da 1 a 4
                for (int i = 0; i < 5; i++) {
                    if (vettore[i].equals("") || !vettore[i].matches("[0-9]+") && !errori) {
                        errori = true;
                    } else if (vettore[i].matches("[0-9]+") && Integer.parseInt(vettore[i]) < 0) {
                        errori = true;
                    }
                    if (errori) {
                        break;
                    }
                }

                //Provincia
                if (!errori) {
                    eProvincia = null;
                    for (Provincia provincia : provinciaList) {
                        if (provincia.getId().equals(Integer.parseInt(vettore[5]))) {
                            eProvincia = provincia;
                            break;
                        }
                    }
                    if (eProvincia == null) {
                        errori = true;
                    }
                }
                if (!errori) {
                    decessiAnnualiList.add(new DecessiAnnuali(Integer.parseInt(vettore[0]), Integer.parseInt(vettore[1]), Integer.parseInt(vettore[2]), Integer.parseInt(vettore[3]), Integer.parseInt(vettore[4]), eProvincia));
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
        new DecessiAnnualiService().saveOrUpdate(decessiAnnualiList);
        try {
            reader.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return new int[]{righeConErrore, righeNonLette};
    }

}
