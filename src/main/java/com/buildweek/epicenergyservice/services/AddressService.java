package com.buildweek.epicenergyservice.services;

import com.buildweek.epicenergyservice.entities.Address;
import com.buildweek.epicenergyservice.exceptions.NotFoundException;
import com.buildweek.epicenergyservice.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    @Autowired
    AddressRepository ar;

    public Address save(Address x) {
        return ar.save(x);
    }

    public Page<Address> getAllPaginate(Pageable p) {
        return ar.findAll(p);
    }

    public List<Address> getAll() {
        return ar.findAll();
    }


    public Address getById(int id) {
        Optional<Address> ba = ar.findById(id);
        if (ba.isEmpty())
            throw new NotFoundException("Address not available");
        return ba.get();
    }

    public Address findByLocalita(String localita) {
        Optional<Address> address = ar.findByLocalita(localita);
        if (address.isEmpty())
            throw new NotFoundException("No user with that address found");
        return address.get();
    }

    public void deleteById(int id) {
        ar.deleteById(id);
    }
}
