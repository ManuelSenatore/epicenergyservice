package com.buildweek.epicenergyservice.controllers;

import com.buildweek.epicenergyservice.entities.Address;
import com.buildweek.epicenergyservice.entities.Client;
import com.buildweek.epicenergyservice.entities.Comuni;
import com.buildweek.epicenergyservice.services.AddressService;
import com.buildweek.epicenergyservice.services.ComuniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/address")
public class AddressController {

    @Autowired
    AddressService as;

    @Autowired
    ComuniService cs;


    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
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

    @GetMapping("/findAll")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<Address>> findAll(){
        return new ResponseEntity<>(as.getAll(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteAddress(@PathVariable("id") int id) {
        as.deleteById(id);
    }
}
