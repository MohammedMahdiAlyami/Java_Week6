package com.example.hw28.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Table(name = "MyOrder")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull(message = "Order quantity must be not empty")
    @Column(columnDefinition = "int not null")
    private Integer quantity;

    @NotNull(message = "totalPrice quantity must be not empty")
    @Column(columnDefinition = "int not null")
    private Integer totalPrice;


    @Column(columnDefinition ="DATE NOT NULL")
    private LocalDate dateReceived;

    @NotNull(message ="status must be not empty" )
    @Pattern(regexp = "^(" + "NEW" + "|" + "INPROGRESS" + "|" + "COMPLETED" + ")", message = "status must be ( NEW )or( INPROGRESS )or( COMPLETED )")
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name ="product_id",referencedColumnName = "id")
    @JsonIgnore
    private Product product;
}