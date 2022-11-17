package com.buildweek.epicenergyservice.controllers;

import com.buildweek.epicenergyservice.entities.Address;
import com.buildweek.epicenergyservice.entities.Comuni;
import com.buildweek.epicenergyservice.services.AddressService;
import com.buildweek.epicenergyservice.services.ComuniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/address")
public class AddressController {

    @Autowired
    AddressService as;

    @Autowired
    ComuniService cs;


    @PostMapping("/add")
    public Address postAddress(
            @RequestParam("via") String via,
            @RequestParam("civico") int civico,
            @RequestParam("localita") String localita,
            @RequestParam("cap") long cap,
            @RequestParam(name = "comune_id", required = false) String comune
    ){
        Comuni newCom = cs.findByComune(comune);

        if(newCom == null){
            Address address = Address.builder()
                    .via(via)
                    .civico(civico)
                    .localita(localita)
                    .cap(cap)
                    .build();

            as.save(address);
            return address;
        } else {
            Address address = Address.builder()
                    .via(via)
                    .civico(civico)
                    .localita(localita)
                    .cap(cap)
                    .comune(newCom)
                    .build();

            as.save(address);
            return address;
        }
    }
}
