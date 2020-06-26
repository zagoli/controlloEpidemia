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
import java.util.List;

public class EtlMalattie {

    final List<Comune> comuneList = new ComuneService().findAll();
    final List<MalattieSettimanali> malattieSettimanaliList = new ArrayList<>();
    Comune eComune = null;

    public void load(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(path), StandardCharsets.UTF_8));
        String riga = reader.readLine();
        String[] vettore;
        while (riga != null && !riga.equals("")) {
            vettore = riga.split(";");
            if (vettore.length == 18) {
                eComune = null;
                for (Comune comune : comuneList) {
                    if (comune.getCodiceIstat().equals(vettore[17])) {
                        eComune = comune;
                        break;
                    }
                }
                    malattieSettimanaliList.add(new MalattieSettimanali(Integer.parseInt(vettore[0]), Integer.parseInt(vettore[1]), Integer.parseInt(vettore[2]), Integer.parseInt(vettore[3]), Integer.parseInt(vettore[4]), Integer.parseInt(vettore[5]), Integer.parseInt(vettore[6]), Integer.parseInt(vettore[7]), Integer.parseInt(vettore[8]), Integer.parseInt(vettore[9]), Integer.parseInt(vettore[10]), Integer.parseInt(vettore[11]), Integer.parseInt(vettore[12]), Integer.parseInt(vettore[13]), Integer.parseInt(vettore[14]), Integer.parseInt(vettore[15]), Integer.parseInt(vettore[16]), eComune));
                }
            riga = reader.readLine();
        }
        new MalattieSettimanaliService().saveOrUpdate(malattieSettimanaliList);
        reader.close();
    }

}
