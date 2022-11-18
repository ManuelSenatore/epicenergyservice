package com.buildweek.epicenergyservice.repositories;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.buildweek.epicenergyservice.entities.Fattura;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FatturaRepository extends JpaRepository<Fattura, Long> {



//  Page<Fattura> findByOrderByDataAsc(Pageable p);
//
//	Page<Fattura> findByOrderByAnnoAsc(Pageable p);
//
//	Page<Fattura> findByOrderByImportoAsc(Pageable p);
//
//	List<Fattura> findAllById(Long id);
//



// Deve essere possibile filtrare le fatture per
// Cliente
// Stato
// Data
// Anno
// Range di importi


    @Query(
         nativeQuery = true,
         value = "SELECT * FROM fatture ORDER BY cliente_id ")
         List<Fattura> findAllAndSortByClients();

    @Query(
        nativeQuery = true,
        value = "SELECT * FROM fatture ORDER BY stato")
        List<Fattura> findAllAndSortByStato();

    @Query(
        nativeQuery = true,
        value ="SELECT * FROM fatture ORDER BY anno")
        List<Fattura> findAllAndSortByAnno();

    @Query(
        nativeQuery = true,
        value ="SELECT * FROM fatture ORDER BY data")
        List<Fattura> findAllAndSortByData();

    @Query(
        nativeQuery = true,
        value ="SELECT * FROM fatture ORDER BY importo")
        List<Fattura> findAllAndSortByImporto();

    /////////////////////////////


    List<Fattura> findAllByCliente(String cliente);


	List<Fattura> findByStato(String stato);


	List<Fattura> findByData(LocalDate data);

	List<Fattura> findByAnno(Integer anno);

    Optional<Fattura> findById(Long id);

	List<Fattura> findByCliente(Long c);

	List<Fattura> findByImportoBetween(Double importoMinimo, Double importoMassimo);


     }
