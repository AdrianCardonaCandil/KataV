package software.ulpgc.model;

import com.google.gson.*;
import java.io.IOException;
import java.net.*;
import java.util.*;

public class FixerAPICurrencyLoader implements CurrencyLoader{
    private static final String url = "http://data.fixer.io/api/symbols?access_key=2cd3afef7c870e2a2866908f9440b7c7";
    private final Set<Currency> currencies = new HashSet<>();

    @Override
    public Set<Currency> loadCurrencies() {
        try {
            this.asSet(readJson());
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return this.currencies;
    }

    private void asSet(String byteFormattedJSON) {
        Map<String, JsonElement> jsonCurrencies = new Gson().fromJson(byteFormattedJSON, JsonObject.class).
                get("symbols").getAsJsonObject().asMap();
        fillCurrenciesSet(jsonCurrencies);
    }

    private void fillCurrenciesSet(Map<String, JsonElement> jsonCurrencies) {
        for (String code : jsonCurrencies.keySet()){
            this.currencies.add(new Currency(code, jsonCurrencies.get(code).getAsString()));
        }
    }

    private String readJson() throws URISyntaxException, IOException {
        return new String(new URI(FixerAPICurrencyLoader.url).toURL().openStream().readAllBytes());
    }
}
