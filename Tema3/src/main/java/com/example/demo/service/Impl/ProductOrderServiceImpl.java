package com.example.demo.service.Impl;

import com.example.demo.dao.ProductOrderRepository;
import com.example.demo.dao.StockRepository;
import com.example.demo.model.Order;
import com.example.demo.model.ProductOrder;
import com.example.demo.model.Stock;
import com.example.demo.service.ProductOrderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductOrderServiceImpl implements ProductOrderService {

    private final ProductOrderRepository productOrderRepository;
    private final StockRepository stockRepository;

    public ProductOrderServiceImpl(ProductOrderRepository productOrderRepository, StockRepository stockRepository) {
        this.productOrderRepository = productOrderRepository;
        this.stockRepository = stockRepository;
    }

    @Override
    public List<ProductOrder> getAllOrdersByOrder(Order order) {
        return productOrderRepository.findAllByOrder(order);
    }

    @Override
    public void save(ProductOrder productOrder) {
        productOrderRepository.save(productOrder);

        Stock stock = productOrder.getProductTrader().getStock();
        stock.setQuantity(stock.getQuantity() - productOrder.getQuantity());
        stockRepository.save(stock);

    }
}
