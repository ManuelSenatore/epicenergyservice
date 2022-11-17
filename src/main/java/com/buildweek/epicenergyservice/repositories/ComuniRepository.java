package com.buildweek.epicenergyservice.repositories;


import com.buildweek.epicenergyservice.entities.Address;
import com.buildweek.epicenergyservice.entities.Comuni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ComuniRepository extends JpaRepository<Comuni, Long> {

    Optional<Comuni> findByComune(String comune);
}
