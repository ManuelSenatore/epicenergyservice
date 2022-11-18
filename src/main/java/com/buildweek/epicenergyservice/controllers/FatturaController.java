package com.buildweek.epicenergyservice.controllers;

import com.buildweek.epicenergyservice.entities.Address;
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
    @PreAuthorize("hasRole('ADMIN')")
    public List<Fattura> getAllFatture() {
        return fs.getAll();
    }

    @GetMapping("/pageable")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<Fattura>> getAllPageable(Pageable p) {
        Page<Fattura> findAll = fs.getAllPaginate(p);

        if (findAll.hasContent()) {
            return new ResponseEntity<>(findAll, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Fattura> getById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(fs.getById(id), HttpStatus.OK);
        } catch (Exception e){
            log.error(e.getMessage());
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }


    @PostMapping("/new")
    @PreAuthorize("hasRole('ADMIN')")
    public Fattura create(
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
           return fattura;
        } catch (Exception e) {
            log.error(e.getMessage());
        }
           return null;
    }

    @PutMapping("")
    @PreAuthorize("hasRole('ADMIN')")
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

    @GetMapping("/findAll")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<Fattura>> findAll(){
        return new ResponseEntity<>(fs.getAll(), HttpStatus.OK);
    }

@GetMapping("/sortbyclients")
 @PreAuthorize("hasRole('ADMIN')")
public ResponseEntity <List<Fattura>> sortByClient(){
    try{
      return new ResponseEntity<List<Fattura>> (fs.findAllAndSortByClients(), HttpStatus.OK);
    } catch (Exception e) {
        log.error(e.getMessage());
    }
    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

}

@GetMapping("/sortbystato")
 @PreAuthorize("hasRole('ADMIN')")
public ResponseEntity <List<Fattura>> sortByStato(){
    try{
      return new ResponseEntity<List<Fattura>> (fs.findAllAndSortByStato(), HttpStatus.OK);
    } catch (Exception e) {
        log.error(e.getMessage());
    }
    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
}

@GetMapping("/sortbyanno")
 @PreAuthorize("hasRole('ADMIN')")
public ResponseEntity <List<Fattura>> sortByAnno(){
    try{
      return new ResponseEntity<List<Fattura>> (fs.findAllAndSortByAnno(), HttpStatus.OK);
    } catch (Exception e) {
        log.error(e.getMessage());
    }
    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
}

@GetMapping("/sortbydata")
 @PreAuthorize("hasRole('ADMIN')")
public ResponseEntity <List<Fattura>> sortByData(){
    try{
      return new ResponseEntity<List<Fattura>> (fs.findAllAndSortByData(), HttpStatus.OK);
    } catch (Exception e) {
        log.error(e.getMessage());
    }
    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
}

@GetMapping("/sortbyimporto")
 @PreAuthorize("hasRole('ADMIN')")
public ResponseEntity <List<Fattura>> sortByImporto(){
    try{
      return new ResponseEntity<List<Fattura>> (fs.findAllAndSortByImporto(), HttpStatus.OK);
    } catch (Exception e) {
        log.error(e.getMessage());
    }
    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
}

@GetMapping("/findbyclient/{id}")
 @PreAuthorize("hasRole('ADMIN')")
public ResponseEntity <List<Fattura>> findByClient(@PathVariable("id") Long id){
    try{
      return new ResponseEntity<List<Fattura>> (fs.findByClient(id), HttpStatus.OK);
    } catch (Exception e) {
        log.error(e.getMessage());
    }
    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
}

@GetMapping("/findbystato/{stato}")
@PreAuthorize("hasRole('ADMIN')")
public ResponseEntity <List<Fattura>> findByStato(@PathVariable("stato") String stato){
    try{
      return new ResponseEntity<List<Fattura>> (fs.findByStato(stato), HttpStatus.OK);
    } catch (Exception e) {
        log.error(e.getMessage());
    }
    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
}

@GetMapping("/findbyanno/{anno}")
 @PreAuthorize("hasRole('ADMIN')")
public ResponseEntity <List<Fattura>> findByAnno(@PathVariable int anno){
    try{
      return new ResponseEntity<List<Fattura>> (fs.findByAnno(anno), HttpStatus.OK);
    } catch (Exception e) {
        log.error(e.getMessage());
    }
    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
}

@GetMapping("/findbydata/{data}")
 @PreAuthorize("hasRole('ADMIN')")
public ResponseEntity <List<Fattura>> findByData(@PathVariable String data){
    try{
      return new ResponseEntity<List<Fattura>> (fs.findByData(LocalDate.parse(data)), HttpStatus.OK);
    } catch (Exception e) {
        log.error(e.getMessage());
    }
    return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
}


}
