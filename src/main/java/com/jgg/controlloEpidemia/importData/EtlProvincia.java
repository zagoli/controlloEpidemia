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
    List<Provincia> provinciaList = new ArrayList<>();
    Regione eRegione = null;

    public void load(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(path), StandardCharsets.UTF_8));
        String riga = reader.readLine();
        String[] vettore;
        while (riga != null && !riga.equals("")) {
            vettore = riga.split(";");
            if (vettore.length == 5) {
                for (Regione regione : regioneList) {
                    eRegione = null;
                    if (regione.getId().equals(Integer.parseInt(vettore[4]))) {
                        eRegione = regione;
                        break;
                    }
                }
                provinciaList.add(new Provincia(Integer.parseInt(vettore[0]), vettore[1], Integer.parseInt(vettore[2]), vettore[3], eRegione));
            }
            riga = reader.readLine();
        }
        new ProvinciaService().saveOrUpdate(provinciaList);
        reader.close();
    }

}
