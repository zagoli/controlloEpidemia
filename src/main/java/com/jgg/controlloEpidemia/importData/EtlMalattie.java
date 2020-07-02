package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.App;
import com.jgg.controlloEpidemia.model.Comune;
import com.jgg.controlloEpidemia.model.MalattieSettimanali;
import com.jgg.controlloEpidemia.service.ComuneService;
import com.jgg.controlloEpidemia.service.MalattieSettimanaliService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EtlMalattie {

    final List<Comune> comuneList = new ComuneService().findAll();
    final List<MalattieSettimanali> malattieSettimanaliList = new ArrayList<>();
    Comune eComune = null;
    boolean errori;

    public int[] load(String path, boolean init) {
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
            if (vettore.length == 18) {
                //Anno
                if (vettore[0].equals("") || !vettore[0].matches("[0-9]+")) {
                    errori = true;
                } else if (vettore[0].matches("[0-9]+") && (Integer.parseInt(vettore[0]) < 1500 || Integer.parseInt(vettore[0]) > Calendar.getInstance().get(Calendar.YEAR))) {
                    errori = true;
                }
                //Settimana
                if (vettore[1].equals("") || !vettore[1].matches("[0-9]+") && !errori) {
                    errori = true;
                } else if (vettore[1].matches("[0-9]+") && (Integer.parseInt(vettore[1]) < 1 || Integer.parseInt(vettore[1]) > 53)) {
                    errori = true;
                }
                //Dati da 2 a 16
                for (int i = 2; i < 17; i++) {
                    if (vettore[i].equals("") || !vettore[i].matches("[0-9]+") && !errori) {
                        errori = true;
                    } else if (vettore[i].matches("[0-9]+") && Integer.parseInt(vettore[i]) < 0) {
                        errori = true;
                    }
                    if (errori) {
                        break;
                    }
                }
                //Comune
                if (!errori) {
                    eComune = null;
                    for (Comune comune : comuneList) {
                        if (comune.getCodiceIstat().equals(vettore[17])) {
                            eComune = comune;
                            break;
                        }
                    }
                    if (eComune == null) {
                        errori = true;
                    }
                }
                if (!errori) {
                    if (!init) {
                        if ((App.utenteCorrente.getComuni().contains(eComune) || App.utenteCorrente.getRuolo().getId() == 1)) {
                            malattieSettimanaliList.add(new MalattieSettimanali(Integer.parseInt(vettore[0]), Integer.parseInt(vettore[1]), Integer.parseInt(vettore[2]), Integer.parseInt(vettore[3]), Integer.parseInt(vettore[4]), Integer.parseInt(vettore[5]), Integer.parseInt(vettore[6]), Integer.parseInt(vettore[7]), Integer.parseInt(vettore[8]), Integer.parseInt(vettore[9]), Integer.parseInt(vettore[10]), Integer.parseInt(vettore[11]), Integer.parseInt(vettore[12]), Integer.parseInt(vettore[13]), Integer.parseInt(vettore[14]), Integer.parseInt(vettore[15]), Integer.parseInt(vettore[16]), eComune));
                        }
                    } else {
                        malattieSettimanaliList.add(new MalattieSettimanali(Integer.parseInt(vettore[0]), Integer.parseInt(vettore[1]), Integer.parseInt(vettore[2]), Integer.parseInt(vettore[3]), Integer.parseInt(vettore[4]), Integer.parseInt(vettore[5]), Integer.parseInt(vettore[6]), Integer.parseInt(vettore[7]), Integer.parseInt(vettore[8]), Integer.parseInt(vettore[9]), Integer.parseInt(vettore[10]), Integer.parseInt(vettore[11]), Integer.parseInt(vettore[12]), Integer.parseInt(vettore[13]), Integer.parseInt(vettore[14]), Integer.parseInt(vettore[15]), Integer.parseInt(vettore[16]), eComune));
                    }
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
        new MalattieSettimanaliService().saveOrUpdate(malattieSettimanaliList);
        try {
            reader.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return new int[]{righeConErrore, righeNonLette};
    }

}
