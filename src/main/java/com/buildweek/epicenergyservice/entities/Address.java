package com.buildweek.epicenergyservice.entities;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "addresses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id", nullable = false)
    private int id;

    private String via;
    private int civico;
    private String localita;
    private long cap;

    @ManyToOne
    @JoinColumn(name = "comune_id")
    private Comuni comune;


}
