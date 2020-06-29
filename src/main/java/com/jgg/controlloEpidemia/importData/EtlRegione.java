package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.model.Regione;
import com.jgg.controlloEpidemia.service.RegioneService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class EtlRegione {

    final List<Regione> regioneList = new ArrayList<>();

    public void load(String path) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(new File(path), StandardCharsets.UTF_8));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        String riga = null;
        try {
            riga = reader.readLine();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        String[] vettore;
        while (riga != null && !riga.equals("")) {
            vettore = riga.split(";");
            if (vettore.length == 3) {
                regioneList.add(new Regione(vettore[0], Integer.parseInt(vettore[1]), vettore[2]));
            }
            try {
                riga = reader.readLine();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        new RegioneService().saveOrUpdate(regioneList);
        try {
            reader.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
