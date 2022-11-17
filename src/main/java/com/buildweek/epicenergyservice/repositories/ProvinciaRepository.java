package com.buildweek.epicenergyservice.repositories;

import com.buildweek.epicenergyservice.entities.Provincia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProvinciaRepository extends JpaRepository<Provincia, Long> {

    Optional<Provincia> findByProvincia(String provincia);
}
