package com.example.demo.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "product_trader", schema = "public")
public class ProductTrader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter @Setter
    @ColumnDefault("false")
    private  boolean subscription;

    @Getter @Setter
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "product_producer_id", referencedColumnName = "id")
    private ProductProducer productProducer;

    @Getter @Setter
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "trader_id", referencedColumnName = "id")
    private User trader;

    @Getter @Setter
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "stock_id", referencedColumnName = "id")
    private Stock stock;

    @Getter @Setter
    @OneToMany(mappedBy = "productTrader", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ProductOrder> productOrders;
}
