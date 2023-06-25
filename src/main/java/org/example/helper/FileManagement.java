package org.example.helper;

import org.example.model.Currency;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManagement {
    private static List<Currency> currencies;

    public static List<Currency> readCurrency(String path) {
        if (currencies != null)
            return currencies;

        currencies = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(" - ");
                if (parts.length == 2) {
                    currencies.add(new Currency(parts[1].trim(), parts[0].trim()));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return currencies;
    }
}
