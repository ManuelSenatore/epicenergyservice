package com.buildweek.epicenergyservice.controllers;

import com.buildweek.epicenergyservice.entities.Address;
import com.buildweek.epicenergyservice.entities.Client;
import com.buildweek.epicenergyservice.entities.RagioneSociale;
import com.buildweek.epicenergyservice.services.AddressService;
import com.buildweek.epicenergyservice.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("api/clients")
public class ClientController {

    @Autowired
    ClientService cs;

    @Autowired
    AddressService as;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public Client postClient(
            @RequestParam("email") String email,
            @RequestParam("ragione_sociale") RagioneSociale ragione_sociale,
            @RequestParam(name = "data_ultimo_contatto", required = false) String data_ultimo_contatto,
            @RequestParam("fatturato_annuale") double fatturato_annuale,
            @RequestParam("pec") String pec,
            @RequestParam("telefono") int telefono,
            @RequestParam("email_contatto") String email_contatto,
            @RequestParam("nome_contatto") String nome_contatto,
            @RequestParam("cognome_contatto") String cognome_contatto,
            @RequestParam("telefono_contatto") int telefono_contatto,
            @RequestParam(name = "indirizzo_legale", required = false) String indirizzo_legale,
            @RequestParam(name = "indirizzo_operativo", required = false) String indirizzo_operativo
            ){

        Address a = as.findByLocalita(indirizzo_legale);
        Address a1 = as.findByLocalita(indirizzo_operativo);
        

        Client c = Client.builder()
                .email(email)
                .dataInserimento(LocalDate.now())
                .ragioneSociale(ragione_sociale)
                .dataUltimoContatto(LocalDate.parse(data_ultimo_contatto))
                .fatturatoAnnuale(fatturato_annuale)
                .pec(pec)
                .telefono(telefono)
                .emailContatto(email_contatto)
                .nomeContatto(nome_contatto)
                .cognomeContatto(cognome_contatto)
                .telefonoContatto(telefono_contatto)
                .indirizzoLegale(a)
                .indirizzoOperativo(a1)
                .build();

        try{
            cs.save(c);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return c;
    }

    //filtra per parte del nome
    @GetMapping("/findbyname/{nomeContatto}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Client>> findByName(@PathVariable String nomeContatto){
        try {
            return new ResponseEntity<>(cs.findByName(nomeContatto), HttpStatus.OK);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

 //filtra per data ultimoContatto
    @GetMapping("/findbyDataUltimoContatto/{c}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Client>> findByDataUltimoContatto(@PathVariable String c){
    	try {
    		return new ResponseEntity<> (cs.findByDataUltimoContatto(LocalDate.parse(c)), HttpStatus.OK);
    	}catch (Exception e) {
    		 System.out.println(e.getMessage());
    	}
    	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


  //filtra per data di inserimento
    @GetMapping("/findbyDataInserimento/{c}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Client>> findByDataInserimento(@PathVariable String c){
    	try {
    		return new ResponseEntity<> (cs.findByDataInserimento(LocalDate.parse(c)), HttpStatus.OK);
    	}catch (Exception e) {
    		 System.out.println(e.getMessage());
    	}
    	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    //filtra per fatturato annuale
    @GetMapping("/findbyFatturatoAnnuale/{fatturatoannuale}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<Client>> findByFatturatoAnnuale(@PathVariable double fatturatoAnnuale){
    	try {
    		return new ResponseEntity<> (cs.findByFatturatoAnnuale(fatturatoAnnuale), HttpStatus.OK);
    	}catch (Exception e) {
    		 System.out.println(e.getMessage());
    	}
    	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }











}
