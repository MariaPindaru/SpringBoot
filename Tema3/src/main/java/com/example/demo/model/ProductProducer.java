package com.example.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product_producer", schema = "public")
public class ProductProducer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Double price;

//    @Getter @Setter
//    @ManyToOne(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private User producer;
}
