package com.example.hw28.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Product name must be not empty")
    @Column(columnDefinition = "varchar(20) not null ")
    private  String name;

    @NotNull(message = "Product price must be not empty ")
    @Column(columnDefinition = "int not null")
    private Integer price;



    @OneToMany(cascade = CascadeType.ALL,mappedBy = "product")
    private Set<Order> orderSet;
}