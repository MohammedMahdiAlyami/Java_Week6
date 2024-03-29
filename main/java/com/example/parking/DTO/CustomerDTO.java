package com.example.parking.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CustomerDTO {

    private String username;
    private String password;
    private String email;

    private String firstName;
    private String lastName;

    private String phoneNum;

    private Double balance;


}
