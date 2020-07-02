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
import java.util.*;

public class EtlComune {

    final List<TipoTerritorio> tipoTerritorioList = new TipoTerritorioService().findAll();
    final List<Provincia> provinciaList = new ProvinciaService().findAll();
    final List<Comune> comuneList = new ArrayList<>();
    TipoTerritorio eTipoTerritorio = null;
    Provincia eProvincia = null;


    public int[] load(String path) {
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
        int righeNonLette = 0;
        int righeConErrore = 0;
        String[] vettore;
        boolean errori;
        while (riga != null) {
            errori = false;
            vettore = riga.split(";");
            if (vettore.length == 7) {
                //Codice istat
                if (vettore[0].length() != 6 || !vettore[0].matches("[0-9]+")) {
                    errori = true;
                }
                //Nome
                if ((vettore[1].equals("") || !vettore[1].matches("[-. a-zA-Z'àâßèéêìö/òôóçüù]+")) && !errori) {
                    errori = true;
                }
                //Superficie
                if ((Integer.parseInt(vettore[2]) < 1 || vettore[2].equals("") || !vettore[0].matches("[0-9]+")) && !errori) {
                    errori = true;
                }
                //Data
                Date data = null;
                if (!errori) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        data = sdf.parse(vettore[3]);
                        Calendar myCalendar = new GregorianCalendar(1500, Calendar.JANUARY, 1);
                        Date dataMin = myCalendar.getTime();
                        if (data.before(dataMin) || data.after(new Date())) {
                            errori = true;
                        }
                    } catch (ParseException e) {
                        errori = true;
                        e.printStackTrace();
                    }
                }
                //Si affaccia sul mare
                boolean siAffacciaSulMare = false;
                if (!vettore[4].matches("[01]") && !errori) {
                    errori = true;
                } else {
                    if (Integer.parseInt(vettore[4]) == 1) {
                        siAffacciaSulMare = true;
                    }
                }
                //Tipo territorio
                if (!errori) {
                    eTipoTerritorio = null;
                    for (TipoTerritorio tipoTerritorio : tipoTerritorioList) {
                        if (tipoTerritorio.getId().equals(Integer.parseInt(vettore[5]))) {
                            eTipoTerritorio = tipoTerritorio;
                            break;
                        }
                    }
                    if (eTipoTerritorio == null) {
                        errori = true;
                    }
                }
                //Provincia
                if (!errori) {
                    eProvincia = null;
                    for (Provincia provincia : provinciaList) {
                        if (provincia.getId().equals(Integer.parseInt(vettore[6]))) {
                            eProvincia = provincia;
                            break;
                        }
                    }
                    if (eProvincia == null) {
                        errori = true;
                    }
                }
                //Aggiungo comune se non ha errori
                if (!errori) {
                    comuneList.add(new Comune(vettore[0], vettore[1], Integer.parseInt(vettore[2]), data, siAffacciaSulMare, eTipoTerritorio, eProvincia));
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
        new ComuneService().saveOrUpdate(comuneList);
        try {
            reader.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
        return new int[]{righeConErrore, righeNonLette};
    }

    public void initLoad(String path) {
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
        boolean errori;
        while (riga != null) {
            errori = false;
            vettore = riga.split(";");
            if (vettore.length == 7) {
                //Codice istat
                if (vettore[0].length() != 6 || !vettore[0].matches("[0-9]+")) {
                    errori = true;
                }
                //Nome
                if ((vettore[1].equals("") || !vettore[1].matches("[-. a-zA-Z'àâßèéêìö/òôóçüù]+")) && !errori) {
                    errori = true;
                }
                //Superficie
                if ((Integer.parseInt(vettore[2]) < 1 || vettore[2].equals("") || !vettore[2].matches("[0-9]+")) && !errori) {
                    errori = true;
                }
                //Data
                Date data = null;
                if (!errori) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        data = sdf.parse(vettore[3]);
                        Calendar myCalendar = new GregorianCalendar(1500, Calendar.JANUARY, 1);
                        Date dataMin = myCalendar.getTime();
                        if (data.before(dataMin) || data.after(new Date())) {
                            errori = true;
                        }
                    } catch (ParseException e) {
                        errori = true;
                        e.printStackTrace();
                    }
                }
                //Si affaccia sul mare
                boolean siAffacciaSulMare = false;
                if (!vettore[4].matches("[01]") && !errori) {
                    errori = true;
                } else {
                    if (Integer.parseInt(vettore[4]) == 1) {
                        siAffacciaSulMare = true;
                    }
                }
                //Tipo territorio
                if (!errori) {
                    eTipoTerritorio = null;
                    for (TipoTerritorio tipoTerritorio : tipoTerritorioList) {
                        if (tipoTerritorio.getId().equals(Integer.parseInt(vettore[5]))) {
                            eTipoTerritorio = tipoTerritorio;
                            break;
                        }
                    }
                    if (eTipoTerritorio == null) {
                        errori = true;
                    }
                }
                //Provincia
                if (!errori) {
                    eProvincia = null;
                    for (Provincia provincia : provinciaList) {
                        if (provincia.getId().equals(Integer.parseInt(vettore[6]))) {
                            eProvincia = provincia;
                            break;
                        }
                    }
                }
                //Aggiungo comune se non ha errori
                if (!errori) {
                    comuneList.add(new Comune(vettore[0], vettore[1], Integer.parseInt(vettore[2]), data, siAffacciaSulMare, eTipoTerritorio, eProvincia));
                }
            }
            try {
                riga = reader.readLine();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        new ComuneService().initSaveOrUpdate(comuneList);
        try {
            reader.close();
        } catch (
                IOException exception) {
            exception.printStackTrace();
        }
    }

}
