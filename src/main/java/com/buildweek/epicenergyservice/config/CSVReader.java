package com.buildweek.epicenergyservice.config;


import com.buildweek.epicenergyservice.entities.Comuni;
import com.buildweek.epicenergyservice.entities.Provincia;
import com.buildweek.epicenergyservice.services.ComuniService;
import com.buildweek.epicenergyservice.services.ProvinciaService;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Getter
@Setter
@Component
public class CSVReader implements CommandLineRunner {
    private static final String CSC_FILE_PATH = "src/main/java/com/buildweek/epicenergyservice/files/province-italiane.csv";
    
    @Autowired
    ProvinciaService ps;

    private static final String CSA_FILE_PATH = "src/main/java/com/buildweek/epicenergyservice/files/comuni-italiani.csv";

    @Autowired
    ComuniService cs;
    
    public static String[] listProvince() throws IOException {

        File file = new File(CSC_FILE_PATH);
        String readString = FileUtils.readFileToString(file, "UTF-8");

        return readString.split("\\r?\\n");
    }
    
    public void registraProvince() throws IOException {
        for (String prov : listProvince()) {
            String[] line = prov.split(";");
            String sigla = line[0];
            String provincia = line[1];
            String regione = line[2];
            Provincia newProv = Provincia.builder().sigla(sigla).provincia(provincia).regione(regione).build();
            System.out.println(newProv);
            ps.save(newProv);

        }
    }

    public static String[] listComuni() throws IOException {

        File file = new File(CSA_FILE_PATH);
        String readString = FileUtils.readFileToString(file, "UTF-8");

        return readString.split("\\r?\\n");
    }

    public void registraComuni() throws IOException {
            for (String com : listComuni()) {
                String[] line = com.split(";");

                String comune = line[2];
                String provincia = line[3];
                Comuni newCom = Comuni.builder().comune(comune).provincia(ps.findByProvincia(provincia)).build();
                System.out.println(newCom);
                cs.save(newCom);
            }

    }

    @Override
    public void run(String... args) throws Exception {
//        registraProvince();
//        registraComuni();
    }
}