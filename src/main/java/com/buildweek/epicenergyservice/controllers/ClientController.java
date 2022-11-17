package com.buildweek.epicenergyservice.controllers;

import com.buildweek.epicenergyservice.entities.Address;
import com.buildweek.epicenergyservice.entities.Client;
import com.buildweek.epicenergyservice.entities.RagioneSociale;
import com.buildweek.epicenergyservice.services.AddressService;
import com.buildweek.epicenergyservice.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
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

//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
//        String dateAsString = data_ultimo_contatto.format(formatter);

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

    public List<Client> findByName(@PathVariable String nomeContatto){
        try {
            return cs.findByName(nomeContatto);
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }













}
