package com.buildweek.epicenergyservice.services;

import com.buildweek.epicenergyservice.entities.Fattura;
import com.buildweek.epicenergyservice.exceptions.NotFoundException;
import com.buildweek.epicenergyservice.repositories.FatturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FatturaService {
    @Autowired
    FatturaRepository fr;

    public Fattura save(Fattura x) {
        return fr.save(x);
    }

    public Page<Fattura> getAllPaginate(Pageable p) {
        return fr.findAll(p);
    }

    public List<Fattura> getAll() {
        return fr.findAll();
    }

    public Fattura getById(Long id) {
        Optional<Fattura> ba = fr.findById(id);
        if (ba.isEmpty())
            throw new NotFoundException("Fattura not available");
        return ba.get();
    }

    public void deleteById(Long id) {
        fr.deleteById(id);
    }

    public List<Fattura> findAllAndSortByClients(){
        return fr.findAllAndSortByClients();
    }
    public List<Fattura> findAllAndSortByStato(){
        return fr.findAllAndSortByStato();
    }
    public List<Fattura> findAllAndSortByAnno(){
        return fr.findAllAndSortByAnno();
    }
    public List<Fattura> findAllAndSortByData(){
        return fr.findAllAndSortByData();
    }
    public List<Fattura> findAllAndSortByImporto(){
        return fr.findAllAndSortByImporto();
    }

    public List<Fattura> findByClient(Long c){
        return fr.findByCliente(c);
    }
    public List<Fattura> findByStato(String s){
        return fr.findByStato(s);
    }
    public List<Fattura> findByAnno(int a){
        return fr.findByAnno(a);
    }
    public List<Fattura> findByData(LocalDate d){
        return fr.findByData(d);
    }

}
