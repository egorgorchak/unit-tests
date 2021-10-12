package ru.codinggym.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "person")
public class User {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    @JsonManagedReference
    private List<Purchase> userPurchases;
}
