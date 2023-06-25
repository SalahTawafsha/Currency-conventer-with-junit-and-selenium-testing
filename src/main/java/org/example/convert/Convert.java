package org.example.convert;

import org.example.model.Currency;

public interface Convert {
    int RATE = 0;
    int RESULT = 1;

    Double[] convert(Currency from, Currency to, Double amount) throws Exception;
}
