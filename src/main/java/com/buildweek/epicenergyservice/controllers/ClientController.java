package com.buildweek.epicenergyservice.controllers;

import com.buildweek.epicenergyservice.entities.Address;
import com.buildweek.epicenergyservice.entities.Client;
import com.buildweek.epicenergyservice.entities.ClienteConverter;
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

    @PostMapping("/new-raw")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Client> create( @RequestBody ClienteConverter clienteConverter ) {
        try {
            return new ResponseEntity<Client>(cs.createAndSave( clienteConverter ), HttpStatus.OK);
        } catch( Exception e ) {
            System.out.println( e.getMessage() );
        }
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    //filtra per parte del nome
    @GetMapping("/findbyname/{nomeContatto}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    //@PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
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
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
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
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
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
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<Client>> findByFatturatoAnnuale(@PathVariable("fatturatoannuale") int fatturatoAnnuale){
    	try {
    		return new ResponseEntity<> (cs.findByFatturatoAnnuale(fatturatoAnnuale), HttpStatus.OK);
    	}catch (Exception e) {
    		 System.out.println(e.getMessage());
    	}
    	return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @GetMapping("/findAll")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<Client>> findAll(){
        return new ResponseEntity<>(cs.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteClient(@PathVariable("id") Long id) {
        cs.deleteById(id);
    }








}
