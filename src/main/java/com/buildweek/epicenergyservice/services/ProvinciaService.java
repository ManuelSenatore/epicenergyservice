package com.buildweek.epicenergyservice.services;

import com.buildweek.epicenergyservice.entities.Provincia;
import com.buildweek.epicenergyservice.exceptions.NotFoundException;
import com.buildweek.epicenergyservice.repositories.ProvinciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProvinciaService {


    @Autowired
    ProvinciaRepository pr;


    public Provincia save(Provincia x) {
       return pr.save(x);
    }

    public Provincia getById(Long id) {
        Optional<Provincia> ba = pr.findById(id);
        if (ba.isEmpty())
            throw new NotFoundException("Provincia not available");
        return ba.get();
    }

    public Provincia findByProvincia(String provincia) {
        Optional<Provincia> prov = pr.findByProvincia(provincia);
        if (prov.isEmpty())
            throw new NotFoundException("No province with that name found");
        return prov.get();
    }

}
