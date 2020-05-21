package com.jgg.controlloEpidemia.importData;

import com.jgg.controlloEpidemia.service.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

public class ImportData {

    ComuneService comuneService = new ComuneService();
    DecessiAnnualiService decessiAnnualiService = new DecessiAnnualiService();
    MalattieSettimanaliService malattieSettimanaliService = new MalattieSettimanaliService();
    PermessoService permessoService = new PermessoService();
    ProvinciaService provinciaService = new ProvinciaService();
    RegioneService regioneService = new RegioneService();
    RuoloService ruoloService = new RuoloService();
    TipoTerritorioService tipoTerritorioService = new TipoTerritorioService();
    UtenteService utenteService = new UtenteService();

    public void importExample() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource("directory")).getFile());
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String row = bufferedReader.readLine();
        while (!row.equals("")) {
            String[] vector = row.split(";");
            //metti la classe dao che ti serve
            //ExampleService.save(new Example(Integer.parseInt(vector[0]), vector[1]));
            row = bufferedReader.readLine();
        }
        bufferedReader.close();
    }
    
}
