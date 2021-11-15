package ru.codinggym.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.AUTO;

@Getter
@Setter
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

    public User(String firstName, String lastName, List<Purchase> userPurchases) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userPurchases = userPurchases;
    }
}
