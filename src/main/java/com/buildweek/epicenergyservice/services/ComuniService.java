package com.buildweek.epicenergyservice.services;

import com.buildweek.epicenergyservice.entities.Address;
import com.buildweek.epicenergyservice.entities.Comuni;
import com.buildweek.epicenergyservice.exceptions.NotFoundException;
import com.buildweek.epicenergyservice.repositories.ComuniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ComuniService {

  @Autowired
  ComuniRepository cr;

    public Comuni save(Comuni x) {
       try {
           return cr.save(x);
       } catch (Exception e){
           throw new NotFoundException("no comune");
       }
    }

    public Comuni findByComune(String comune) {
        Optional<Comuni> comu = cr.findByComune(comune);
        if (comu.isEmpty())
            throw new NotFoundException("No comune with that address found");
        return comu.get();
    }

}
