package com.buildweek.epicenergyservice.services;

import com.buildweek.epicenergyservice.entities.Client;
import com.buildweek.epicenergyservice.entities.ClienteConverter;
import com.buildweek.epicenergyservice.exceptions.NotFoundException;
import com.buildweek.epicenergyservice.repositories.AddressRepository;
import com.buildweek.epicenergyservice.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
   @Autowired
   ClientRepository cr;

    @Autowired
    AddressService as;

    public List<Client> findAllOrderByIndirizzoLegaleComuneProvinciaAsc(String provincia){
       return cr.findAllOrderByIndirizzoLegaleComuneProvinciaProvincia(provincia);
    }
    public Page<Client> findAllSortProvincia(){
       return cr.findAll(PageRequest.of(0, 10, Sort.by(Sort.Direction.ASC, "indirizzo_legale.comune.provincia.provincia")));
    }
    public List<Client> findAllAndSortByName(){
       return cr.findAllAndSortByName();
    }
    public List<Client> findAllAndSortByFatturato(){
       return cr.findAllAndSortByFatturato();
    }
    public List<Client> findAllAndSortByDataInserimento(){
       return cr.findAllAndSortByDataInserimento();
    }
    public List<Client> findAllAndSortByDataUltimoContatto(){
       return cr.findAllAndSortByDataUltimoContatto();
    }
    public List<Client> findByName(String c){
      return cr.findByNomeContattoContaining(c);
   }

   public List<Client> findByFatturatoAnnuale(int c){
      return cr.findByFatturatoAnnuale(c);
   }

   public List<Client> findByDataInserimento(LocalDate c){
      return cr.findByDataInserimento(c);
   }

   public List<Client> findByDataUltimoContatto(LocalDate c){
      return cr.findByDataUltimoContatto(c);
   }



    public Client save(Client x) {
        return cr.save(x);
    }

    public Client createAndSave( ClienteConverter clienteConverter ) throws Exception {
        Client cliente = Client.builder()
                .indirizzoLegale( as.getById(clienteConverter.getIndirizzoLegaleId()) )
                .indirizzoOperativo( as.getById(clienteConverter.getIndirizzoOperativoId()) )
                .email( clienteConverter.getEmail() )
                .pec( clienteConverter.getPec() )
                .emailContatto( clienteConverter.getEmailContatto() )
                .nomeContatto( clienteConverter.getNomeContatto() )
                .cognomeContatto( clienteConverter.getCognomeContatto() )
                .telefonoContatto( clienteConverter.getTelefonoContatto() )
                .ragioneSociale( clienteConverter.getRagioneSociale())
                .fatturatoAnnuale( clienteConverter.getFatturatoAnnuo() )
                .dataInserimento( LocalDate.now() )
                .dataUltimoContatto( LocalDate.now() )
                .build();

        return cr.save(cliente);
    };
    public Client getById(Long id) {
        Optional<Client> ba = cr.findById(id);
        if (ba.isEmpty())
            throw new NotFoundException("Client not available");
        return ba.get();
    }

   public void deleteById(Long id) {
      try {
       cr.deleteById(id);
      } catch(Exception e) {
      throw new NotFoundException("Client not deleteable");
      }
   }

   public List<Client> findAll(){

      return cr.findAll();
   }

//     Deve essere possibile ordinare i clienti per:
// Nome
// Fatturato annuale
// Data di inserimento
// Data di ultimo contatto
// Provincia della sede legale.



// public Page<Client> OrderByName(Pageable p){
//     return cr.findByOrderByNomeContattoAsc(p);
       
// }
// public Page<Client> findAll(Pageable pageable){
//     List<Passenger> passengers = repository.findAll(Sort.by(Sort.Direction.ASC, "seatNumber"));
// return cr.findAll(pageable);
// }



// public Client save(Client c) {
//     return cr.save(c);
// }


//Query q = @Query("SELECT * u FROM Client u WHERE u.fatturatoAnnuale = 1")
//Collection<User> findAllActiveUsers();
}
