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
import java.util.List;

public class EtlDecessi {

    final List<Provincia> provinciaList = new ProvinciaService().findAll();
    List<DecessiAnnuali> decessiAnnualiList = new ArrayList<>();
    Provincia eProvincia = null;

    public void load(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File(path), StandardCharsets.UTF_8));
        String riga = reader.readLine();
        String[] vettore;
        while (riga != null && !riga.equals("")) {
            vettore = riga.split(";");
            if (vettore.length == 6) {
                eProvincia = null;
                for (Provincia provincia : provinciaList) {
                    if (provincia.getId().equals(Integer.parseInt(vettore[5]))) {
                        eProvincia = provincia;
                        break;
                    }
                }
                decessiAnnualiList.add(new DecessiAnnuali(Integer.parseInt(vettore[0]), Integer.parseInt(vettore[1]), Integer.parseInt(vettore[2]), Integer.parseInt(vettore[3]), Integer.parseInt(vettore[4]), eProvincia));
            }
            riga = reader.readLine();
        }
        new DecessiAnnualiService().saveOrUpdate(decessiAnnualiList);
        reader.close();
    }

}
