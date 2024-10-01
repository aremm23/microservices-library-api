package com.artsem.api.crudservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "car")
public class Car {
    @Column(name = "price")
    @JdbcTypeCode(SqlTypes.DOUBLE)
    private Double price;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_seq")
    @SequenceGenerator(name = "car_seq")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "brand", nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private String brand;

//    /*@OneToMany(mappedBy = "car", orphanRemoval = true)
//    private List<UserDetails> users = new ArrayList<>();
//*/
}