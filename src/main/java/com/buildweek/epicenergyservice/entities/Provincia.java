package com.buildweek.epicenergyservice.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;


import javax.persistence.*;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Builder
public class Provincia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public String sigla;
    public String provincia;
    public String regione;

    @OneToMany(mappedBy = "provincia")
    @JsonBackReference
    @ToString.Exclude
    public List<Comuni> comuni;



}
