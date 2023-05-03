package com.example.account.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String surname;
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
    private Set<Account> accounts = new HashSet<>();
}
