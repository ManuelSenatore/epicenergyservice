package com.buildweek.epicenergyservice.controllers;

import com.buildweek.epicenergyservice.entities.Client;
import com.buildweek.epicenergyservice.entities.Fattura;
import com.buildweek.epicenergyservice.entities.StatoFattura;
import com.buildweek.epicenergyservice.services.ClientService;
import com.buildweek.epicenergyservice.services.FatturaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/fatture")
public class FatturaController {


    @Autowired
    private FatturaService fs;

    @Autowired
    private ClientService cs;


    @GetMapping("")
    @CrossOrigin
    public List<Fattura> getAllFatture() {
        return fs.getAll();
    }

    @GetMapping("/pageable")
    public ResponseEntity<Page<Fattura>> getAllPageable(Pageable p) {
        Page<Fattura> findAll = fs.getAllPaginate(p);

        if (findAll.hasContent()) {
            return new ResponseEntity<>(findAll, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<Fattura> getById(@PathVariable Long id) {

        return new ResponseEntity<>(fs.getById(id), HttpStatus.OK);

    }


    @PostMapping("/new")
    public void create(
            @RequestParam("anno") int anno,
            @RequestParam("imp") Double imp,
            @RequestParam("id_cliente") Long id_cliente
            ) {
           Client newclient = cs.getById(id_cliente);

           Fattura fattura = Fattura.builder()
            				.anno(anno)
            				.importo(imp)
            				.data(LocalDate.now())
            				.stato(StatoFattura.NONPAGATA)
            				.cliente(newclient)
            				.build();
           try {
           fs.save(fattura);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @PutMapping("")
    public void update(@RequestBody Fattura fattura) {
        try {
            fs.save(fattura);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    // @PutMapping("/{id}/Stato")
    // public void putStato(@PathVariable("id") Long id) {
    //     Fattura f = fs.getById(id);
    //     f.putStato(fs.getByRole(roleType));


    //     us.update(u);
    // }

    @DeleteMapping("/deletefattura/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteById(@PathVariable Long id) {
        try {
            fs.deleteById(id);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

@GetMapping("/sortbyclients")
 @PreAuthorize("hasRole('ADMIN')")
public ResponseEntity <List<Fattura>> sortByClient(){
    try{
      return (ResponseEntity<List<Fattura>>) fs.findAllAndSortByClients();
    } catch (Exception e) {
        log.error(e.getMessage());
    }
    return null;

}

@GetMapping("/sortbystato")
 @PreAuthorize("hasRole('ADMIN')")
public ResponseEntity <List<Fattura>> sortByStato(){
    try{
      return (ResponseEntity<List<Fattura>>) fs.findAllAndSortByStato();
    } catch (Exception e) {
        log.error(e.getMessage());
    }
    return null;
}

@GetMapping("/sortbyanno")
 @PreAuthorize("hasRole('ADMIN')")
public ResponseEntity <List<Fattura>> sortByAnno(){
    try{
      return (ResponseEntity<List<Fattura>>) fs.findAllAndSortByAnno();
    } catch (Exception e) {
        log.error(e.getMessage());
    }
    return null;
}

@GetMapping("/sortbydata")
 @PreAuthorize("hasRole('ADMIN')")
public ResponseEntity <List<Fattura>> sortByData(){
    try{
      return (ResponseEntity<List<Fattura>>) fs.findAllAndSortByData();
    } catch (Exception e) {
        log.error(e.getMessage());
    }
    return null;
}

@GetMapping("/sortbyimporto")
 @PreAuthorize("hasRole('ADMIN')")
public ResponseEntity <List<Fattura>> sortByImporto(){
    try{
      return (ResponseEntity<List<Fattura>>) fs.findAllAndSortByImporto();
    } catch (Exception e) {
        log.error(e.getMessage());
    }
    return null;
}

@GetMapping("/findbyclient")
 @PreAuthorize("hasRole('ADMIN')")
public ResponseEntity <List<Fattura>> findByClient(@PathVariable String cognomeContatto){
    try{
      return (ResponseEntity<List<Fattura>>) fs.findByClient(cognomeContatto);
    } catch (Exception e) {
        log.error(e.getMessage());
    }
    return null;
}

@GetMapping("/findbystato")
 @PreAuthorize("hasRole('ADMIN')")
public ResponseEntity <List<Fattura>> findByStato(@PathVariable String stato){
    try{
      return (ResponseEntity<List<Fattura>>) fs.findByStato(stato);
    } catch (Exception e) {
        log.error(e.getMessage());
    }
    return null;
}

@GetMapping("/findbyanno/{anno}")
 @PreAuthorize("hasRole('ADMIN')")
public ResponseEntity <List<Fattura>> findByAnno(@PathVariable int anno){
    try{
      return (ResponseEntity<List<Fattura>>) fs.findByAnno(anno);
    } catch (Exception e) {
        log.error(e.getMessage());
    }
    return null;
}

@GetMapping("/findbydata/{data}")
 @PreAuthorize("hasRole('ADMIN')")
public ResponseEntity <List<Fattura>> findByData(@PathVariable LocalDate data){
    try{
      return (ResponseEntity<List<Fattura>>) fs.findByData(data);
    } catch (Exception e) {
        log.error(e.getMessage());
    }
    return null;
}




//      @Query(
//             "Select f FROM fatture f WHERE DATE(f.data) = :c"
//      )
//      List<Fattura> findByData(@Param("d") LocalDate d);


//      }

}
