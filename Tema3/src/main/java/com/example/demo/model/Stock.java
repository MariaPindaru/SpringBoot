package com.example.demo.model;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "stock", schema = "public")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private Double maxQuantity;

    @Getter
    @Setter
    private Double minQuantity;

    @Getter
    @Setter
    private Double quantity;


}
