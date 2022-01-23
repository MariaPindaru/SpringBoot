package com.example.demo.components.validator;

import com.example.demo.dto.ProductTraderCreationDto;
import com.example.demo.service.ProductTraderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProductTraderCreationValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductTraderCreationDto productTrader = (ProductTraderCreationDto) target;

        boolean anyIsNull = false;

        if (productTrader.getProductProducerId() == null) {
            errors.rejectValue("productProducerId", "NotEmpty");
            anyIsNull = true;
        }

        if (productTrader.getMinQuantity() == null) {
            errors.rejectValue("minQuantity", "NotEmpty");
            anyIsNull = true;
        }

        if (productTrader.getMaxQuantity() == null) {
            errors.rejectValue("maxQuantity", "NotEmpty");
            anyIsNull = true;
        }

        if (productTrader.getBuyQuantity() == null) {
            errors.rejectValue("buyQuantity", "NotEmpty");
            anyIsNull = true;
        }

        if(anyIsNull) return;

        if (productTrader.getMaxQuantity() < productTrader.getMinQuantity()) {
            errors.rejectValue("minQuantity", "Quantity.minGreater");
        }

        if (productTrader.getMinQuantity() < 0 ) {
            errors.rejectValue("minQuantity", "Quantity.minnNegative");
        }

        if (productTrader.getMaxQuantity().equals(Double.MAX_VALUE)) {
            errors.rejectValue("maxQuantity", "Quantity.max");
        }

        if (productTrader.getBuyQuantity() < productTrader.getMinQuantity()) {
            errors.rejectValue("buyQuantity", "Quantity.buyLower");
        }

        if (productTrader.getBuyQuantity() > productTrader.getMaxQuantity()) {
            errors.rejectValue("buyQuantity", "Quantity.buyGreater");
        }
    }
}
