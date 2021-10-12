package ru.codinggym.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = AUTO)
    @Column(name = "purchase_id")
    private Long purchaseId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    @JsonBackReference
    private User user;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    private PurchaseCategory category;

    @Column(name = "content")
    private String content;

    @Column(name = "purchase_sum")
    private Double purchaseSum;
}
