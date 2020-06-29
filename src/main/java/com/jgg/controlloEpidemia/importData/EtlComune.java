package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.model.Comune;
import com.jgg.controlloEpidemia.model.Provincia;
import com.jgg.controlloEpidemia.model.TipoTerritorio;
import com.jgg.controlloEpidemia.service.ComuneService;
import com.jgg.controlloEpidemia.service.ProvinciaService;
import com.jgg.controlloEpidemia.service.TipoTerritorioService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EtlComune {

    final List<TipoTerritorio> tipoTerritorioList = new TipoTerritorioService().findAll();
    final List<Provincia> provinciaList = new ProvinciaService().findAll();
    final List<Comune> comuneList = new ArrayList<>();
    TipoTerritorio eTipoTerritorio = null;
    Provincia eProvincia = null;

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
            if (vettore.length == 7) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date data = null;
                try {
                    data = sdf.parse(vettore[3]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                boolean siAffacciaSulMare = false;
                if (Integer.parseInt(vettore[4]) == 1) {
                    siAffacciaSulMare = true;
                }
                eTipoTerritorio = null;
                for (TipoTerritorio tipoTerritorio : tipoTerritorioList) {
                    if (tipoTerritorio.getId().equals(Integer.parseInt(vettore[5]))) {
                        eTipoTerritorio = tipoTerritorio;
                        break;
                    }
                }
                eProvincia = null;
                for (Provincia provincia : provinciaList) {
                    if (provincia.getId().equals(Integer.parseInt(vettore[6]))) {
                        eProvincia = provincia;
                        break;
                    }
                }
                comuneList.add(new Comune(vettore[0], vettore[1], Integer.parseInt(vettore[2]), data, siAffacciaSulMare, eTipoTerritorio, eProvincia));
            }
            try {
                riga = reader.readLine();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        new ComuneService().saveOrUpdate(comuneList);
        try {
            reader.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

}
