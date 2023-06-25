package org.example.API;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.example.helper.FileManagement;
import org.example.model.Currency;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Convert {
    public static final int RATE = 0;
    public static final int RESULT = 1;
    private static String KEY = "2e2c6f7fc6314770c6bc3b3b";

    public static Double[] convertByAPI(Currency from, Currency to, Double amount) {
        if (from == null || to == null || amount == null) throw new NullPointerException("You must enter all fields.");

        if (amount < 0) throw new IllegalArgumentException("Amount must be positive.");

        if (!FileManagement.readCurrency("currencyInput.txt").contains(from) || !FileManagement.readCurrency("currencyInput.txt").contains(to))
            throw new IllegalArgumentException("You must select currency from the list.");
        try {
            String url_str = "https://v6.exchangerate-api.com/v6/" + KEY + "/pair/" + from.getCode() + "/" + to.getCode();

            // Making Request

            URL url = new URL(url_str);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            // Convert to JSON
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject jsonObj = root.getAsJsonObject();

            // Get rate and calculate result
            Double rate = Double.parseDouble(jsonObj.get("conversion_rate").toString());
            double result = amount * rate;

            return new Double[] {rate, result};

        } catch (IOException | IllegalStateException ignored) {
            throw new IllegalStateException("API is not working");
        } catch (NumberFormatException e) {
            throw new NumberFormatException("You must enter only numbers in amount.");
        } catch (NullPointerException e) {
            throw new NullPointerException("Please select From and to currency.");
        }
    }

    public static void getAll(Currency from) {

        try {
            String url_str = "https://v6.exchangerate-api.com/v6/" + KEY + "/latest/" + from.getCode();

            // Making Request

            URL url = new URL(url_str);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            // Convert to JSON
            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject jsonObj = root.getAsJsonObject();


            jsonObj.get("conversion_rates").getAsJsonObject().entrySet().forEach(e -> {
                System.out.println("INSERT INTO `currency`(`name`, `code`, `rate`) VALUES (" + e.getKey() + "," + e.getValue() + ")");
            });


        } catch (IOException | IllegalStateException ignored) {
            throw new IllegalStateException("API is not working");
        } catch (NumberFormatException e) {
            throw new NumberFormatException("You must enter only numbers in amount.");
        } catch (NullPointerException e) {
            throw new NullPointerException("Please select From and to currency.");
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File("input.txt"));

        while (scan.hasNextLine()) {
            String line = scan.nextLine();
            String[] parts = line.split(",");
            System.out.println("INSERT INTO `currency`(`name`, `code`, `rate`) VALUES ('" + parts[0].substring(0, parts[0].length() - 6) + "','" + parts[1].trim() + "'," + parts[2] + ");");

        }
    }
}
