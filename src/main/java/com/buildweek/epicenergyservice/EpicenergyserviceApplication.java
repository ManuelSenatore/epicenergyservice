package com.buildweek.epicenergyservice;

import com.buildweek.epicenergyservice.entities.Client;
import com.buildweek.epicenergyservice.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Pageable;

import java.io.IOException;

@SpringBootApplication
public class EpicenergyserviceApplication {



	public static void main(String[] args) throws IOException {
		SpringApplication.run(EpicenergyserviceApplication.class, args);

		System.out.println("ciao");

		Client c1 =  Client.builder().nomeContatto("ciao").build();
		Client c2 =  Client.builder().nomeContatto("diao").build();

//		cs.save(c1);
//		cs.save(c2);
//
//
//		System.out.println(cs.OrderByName(Pageable pageable);


	}
	

}
