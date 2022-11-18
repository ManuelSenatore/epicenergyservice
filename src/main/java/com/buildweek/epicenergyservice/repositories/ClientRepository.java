package com.buildweek.epicenergyservice.repositories;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.buildweek.epicenergyservice.entities.Client;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository  extends JpaRepository<Client, Long> {

// Deve essere possibile ordinare i clienti per:
// Nome
// Fatturato annuale
// Data di inserimento
// Data di ultimo contatto
// Provincia della sede legale.

// Deve essere possibile filtrare la lista clienti per:
// Fatturato annuale
// Data di inserimento
// Data di ultimo contatto
// Parte del nome

     @Query(
             nativeQuery = true,
             value = "SELECT * FROM clients ORDER BY nome_contatto ")
     List<Client> findAllAndSortByName();

     @Query(
               nativeQuery = true,
               value = "SELECT * FROM clients ORDER BY fatturato_annuale ")
     List<Client> findAllAndSortByFatturato();

     @Query(
               nativeQuery = true,
               value = "SELECT * FROM clients ORDER BY data_inserimento ")
     List<Client> findAllAndSortByDataInserimento();

       @Query(
               nativeQuery = true,
               value = "SELECT * FROM clients ORDER BY data_ultimo_contatto ")
     List<Client> findAllAndSortByDataUltimoContatto();



   //    @Query(
   //            nativeQuery = true,
   //            value = "SELECT * FROM clients ORDER BY indirizzo_legale ")
    // List<User> findAllAndSortByProvinciaSedeLegale();

     List<Client> findAllOrderByIndirizzoLegaleComuneProvinciaProvincia(String provincia);

//     @Query(
//            "Select c FROM clients c WHERE nomeContatto LIKE concat('%', :c ,'%')"
//     )
//     List<Client> findByName(@Param("c") String c);

     List<Client> findByNomeContattoContaining(String nomeContatto);

//     @Query(
//            "Select c FROM clients c WHERE fatturatoAnnuale <= :c"
//     )
//     List<Client> findByFatturatoAnnuale(@Param("c") double c);
//
//     @Query(
//            "Select c FROM clients c WHERE DATE(dataInserimento) = :c"
//     )
//     List<Client> findByDataInserimento(@Param("c") LocalDate c);
//
//     @Query(
//            "Select c FROM clients c WHERE DATE(dataUltimoContatto) = :c"
//     )
//     List<Client> findByDataUltimoContatto(@Param("c") LocalDate c);

//    Page<Client> findByRagioneSocialeContainingIgnoreCase(String nomeContatto, Pageable p);
//
    List<Client> findByFatturatoAnnuale(int fatturatoAnnuale);

    List<Client> findByDataInserimento(LocalDate dataInserimento);

    List<Client> findByDataUltimoContatto(LocalDate dataUltimoContatto);
//
//    Page<Client> findByOrderByIndirizzoSedeOperativaComuneProvinciaNomeAsc(Pageable p);
//
//    Page<Client> findByOrderByDataUltimoContattoAsc(Pageable p);
//
//    Page<Client> findByOrderByDataInserimentoAsc(Pageable p);
//
//    Page<Client> findByOrderByRagioneSocialeAsc(Pageable p);
//
//    Page<Client> findByOrderByFatturatoAnnualeAsc(Pageable p);



// Deve essere possibile filtrare la lista clienti per:
// Fatturato annuale
// Data di inserimento
// Data di ultimo contatto
// Parte del nome

}


