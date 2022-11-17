package com.buildweek.epicenergyservice.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;


import javax.persistence.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Builder
public class Comuni {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    public String comune;


    @ManyToOne
    @JoinColumn(name = "provincia_id")
    @JsonManagedReference
    public Provincia provincia;





}
