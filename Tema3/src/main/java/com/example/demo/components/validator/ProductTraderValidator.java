package com.example.demo.components.validator;

import com.example.demo.dto.ProductTraderDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProductTraderValidator  implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductTraderDto productTrader = (ProductTraderDto) target;

        boolean anyIsNull = false;

        if (productTrader.getMinQuantity() == null) {
            errors.rejectValue("minQuantity", "NotEmpty");
            anyIsNull = true;
        }

        if (productTrader.getMaxQuantity() == null) {
            errors.rejectValue("maxQuantity", "NotEmpty");
            anyIsNull = true;
        }

        if (productTrader.getQuantity() == null) {
            errors.rejectValue("buyQuantity", "NotEmpty");
            anyIsNull = true;
        }

        if(anyIsNull) return;

        if (productTrader.getMaxQuantity() < productTrader.getMinQuantity()) {
            errors.rejectValue("minQuantity", "Quantity.minGreater");
        }

        if (productTrader.getMinQuantity() < 0 ) {
            errors.rejectValue("minQuantity", "Quantity.minNegative");
        }

        if (productTrader.getMaxQuantity().equals(Double.MAX_VALUE)) {
            errors.rejectValue("maxQuantity", "Quantity.max");
        }

        if (productTrader.getQuantity() != 0 && productTrader.getQuantity() < productTrader.getMinQuantity()) {
            errors.rejectValue("quantity", "Quantity.buyLower");
        }

        if (productTrader.getQuantity() > productTrader.getMaxQuantity()) {
            errors.rejectValue("quantity", "Quantity.buyGreater");
        }
    }
}
