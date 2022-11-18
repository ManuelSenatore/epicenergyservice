package com.buildweek.epicenergyservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Builder
@Table(name = "clients")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long partitaIva;

    private String email;
    private LocalDate dataInserimento;
    @Enumerated(EnumType.STRING)
    private RagioneSociale ragioneSociale;
    private LocalDate dataUltimoContatto;
    private int fatturatoAnnuale;
    private String pec;
    private String emailContatto;
    private String nomeContatto;
    private String cognomeContatto;
    private int telefonoContatto;

    @ManyToOne
    @JoinColumn(name = "indirizzo_legale_ID")
    private Address indirizzoLegale;

    @ManyToOne
    @JoinColumn(name = "indirizzo_operativo_ID")
    private Address indirizzoOperativo;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    @JsonBackReference
    @ToString.Exclude
    private Set<Fattura> fatture;

}
