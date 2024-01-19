package software.ulpgc.model;

import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.net.*;
import com.google.gson.*;

public class FixedAPIExchangeRateLoader implements ExchangeRateLoader{
    private static final String url = "http://data.fixer.io/api/latest&base=EUR?access_key=2cd3afef7c870e2a2866908f9440b7c7";
    private final Map<String, Double> eurRates = new HashMap<>();
    private LocalDate date;

    @Override
    public ExchangeRate loadExchangeRate(Currency base, Currency quote) {
        if (base.code().equals("EUR")){
            return new ExchangeRate(base, quote, this.date, eurRates.get(quote.code()));
        }
        return new ExchangeRate(base, quote, this.date, computeRate(base.code(), quote.code()));
    }

    private double computeRate(String baseCode, String quoteCode) {
        return (1 / eurRates.get(baseCode) * eurRates.get(quoteCode));
    }

    public FixedAPIExchangeRateLoader() {
        loadData();
    }

    private void loadData() {
        try {
            decode(loadJson());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    private void decode(String byteFormattedJson) {
        JsonObject json = new Gson().fromJson(byteFormattedJson, JsonObject.class);
        fillEurRates(json.get("rates").getAsJsonObject().asMap());
        setDate(json.get("date").getAsString());
    }

    private void setDate(String date) {
        this.date = LocalDate.parse(date);
    }

    private void fillEurRates(Map<String, JsonElement> jsonRates) {
        for (String currency : jsonRates.keySet()){
            this.eurRates.put(currency, jsonRates.get(currency).getAsDouble());
        }
    }

    private String loadJson() throws URISyntaxException, IOException {
        return new String(new URI(FixedAPIExchangeRateLoader.url).toURL().openStream().readAllBytes());
    }
}
