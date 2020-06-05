package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.model.MalattieSettimanali;
import com.jgg.controlloEpidemia.service.ComuneService;
import com.jgg.controlloEpidemia.service.MalattieSettimanaliService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class EtlMalattie {

    final ComuneService comuneService = new ComuneService();
    final MalattieSettimanaliService malattieSettimanaliService = new MalattieSettimanaliService();

    private void caricaMalattia(String[] vett) {
        MalattieSettimanali malattieSettimanali = new MalattieSettimanali(Integer.parseInt(vett[0]), Integer.parseInt(vett[1]), Integer.parseInt(vett[2]), Integer.parseInt(vett[3]), Integer.parseInt(vett[4]), Integer.parseInt(vett[5]), Integer.parseInt(vett[6]), Integer.parseInt(vett[7]), Integer.parseInt(vett[8]), Integer.parseInt(vett[9]), Integer.parseInt(vett[10]), Integer.parseInt(vett[11]), Integer.parseInt(vett[12]), Integer.parseInt(vett[13]), Integer.parseInt(vett[14]), Integer.parseInt(vett[15]), Integer.parseInt(vett[16]), comuneService.findByCodiceIstat(Integer.parseInt(vett[17])));
        malattieSettimanaliService.save(malattieSettimanali);
    }

    public void load(String path) throws IOException {
        File fileMalattie = new File(path);
        BufferedReader reader = new BufferedReader(new FileReader(fileMalattie));
        String riga = reader.readLine();
        String[] vettore;
        while (riga != null && !riga.equals("")) {
            vettore = riga.split(";");
            if (vettore.length == 18) {
                caricaMalattia(vettore);
            }
            riga = reader.readLine();
        }
        reader.close();
    }
}
