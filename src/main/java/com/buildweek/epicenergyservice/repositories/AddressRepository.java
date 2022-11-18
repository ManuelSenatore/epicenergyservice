package com.buildweek.epicenergyservice.repositories;

import com.buildweek.epicenergyservice.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

    Optional<Address> findByLocalita(String localita);
}
