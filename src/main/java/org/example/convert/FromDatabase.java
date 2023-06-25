package org.example.convert;


import org.example.database.Database;
import org.example.helper.FileManagement;
import org.example.model.Currency;


public class FromDatabase implements Convert {

    public Double[] convert(Currency from, Currency to, Double amount) {
        if (from == null || to == null || amount == null) throw new NullPointerException("You must enter all fields.");

        if (amount < 0) throw new IllegalArgumentException("Amount must be positive.");

        if (!FileManagement.readCurrency("currencyInput.txt").contains(from) || !FileManagement.readCurrency("currencyInput.txt").contains(to))
            throw new IllegalArgumentException("You must select currency from the list.");
        try {

            Database database = new Database("localhost", "salah", "Salah2023", "currency");


            Double rate;
            double result;

            if (from.getCode().equals("USD")) {
                rate = database.getRate(to.getCode());
            } else {
                Double rateFrom = database.getRate(from.getCode());
                rate = (1 / rateFrom);
                rate *= database.getRate(to.getCode());
            }
            result = amount * rate;

            return new Double[]{rate, result};

        } catch (IllegalStateException ignored) {
            throw new IllegalStateException("FromAPI is not working");
        }
    }
}
