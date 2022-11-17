package com.buildweek.epicenergyservice.config;

import com.buildweek.epicenergyservice.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ClientiRunner implements CommandLineRunner {

    @Autowired
    ClientService cs;
    
    @Override
    public void run(String... args) throws Exception {
        //System.out.println(cs.findAllOrderByIndirizzoLegaleComuneProvinciaAsc().toString());
    }
}
