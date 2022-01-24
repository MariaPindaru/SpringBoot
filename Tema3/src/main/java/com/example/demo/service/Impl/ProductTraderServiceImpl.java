package com.example.demo.service.Impl;

import com.example.demo.dao.ProductTraderRepository;
import com.example.demo.dao.StockRepository;
import com.example.demo.model.ProductTrader;
import com.example.demo.model.Stock;
import com.example.demo.service.ProductTraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductTraderServiceImpl implements ProductTraderService {

    private final ProductTraderRepository productTraderRepository;
    private final StockRepository stockRepository;

    @Autowired
    public ProductTraderServiceImpl(ProductTraderRepository productTraderRepository, StockRepository stockRepository) {
        this.productTraderRepository = productTraderRepository;
        this.stockRepository = stockRepository;
    }


    @Override
    public List<ProductTrader> getAllTraderProducts() {
        return this.productTraderRepository.findAll();
    }

    @Override
    public List<ProductTrader> getProductsByTraderId(Long id) {
        return this.productTraderRepository.findAllByUserId(id);
    }

    @Override
    public Optional<ProductTrader> getProductById(Long id) {
        return this.productTraderRepository.findById(id);
    }

    @Override
    public ProductTrader getProductByTraderNameAndProductName(String trader, String product) {
        return this.productTraderRepository.findByTraderNameAndProductName(trader, product);
    }

    @Override
    public void handleStockChange(ProductTrader productTrader) {

        Stock stock = productTrader.getStock();
        if(stock.getQuantity() <= stock.getMinQuantity())
            stock.setQuantity(stock.getMaxQuantity());

        stockRepository.save(stock);
    }

    @Override
    public void save(ProductTrader productTrader) {
        this.productTraderRepository.save(productTrader);
    }
}
