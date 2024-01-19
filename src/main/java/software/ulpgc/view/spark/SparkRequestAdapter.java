package software.ulpgc.view.spark;

import software.ulpgc.model.Currency;
import software.ulpgc.model.FixerAPICurrencyLoader;
import software.ulpgc.model.Money;
import spark.Spark;

import java.util.Set;

public class SparkRequestAdapter implements Input {

    /*
     * Adapts from Spark Input Into Self Classes.
     * */

    private final Set<Currency> currencies;
    private String baseCode;
    private String quoteCode;
    private double amount;

    public SparkRequestAdapter() {
        this.currencies = new FixerAPICurrencyLoader().loadCurrencies();
        this.beginListen();
    }

    private void beginListen() {
        Spark.before("/exchange/:amount/:quote/:base", ((request, response) -> {
            this.baseCode = request.params(":base");
            this.quoteCode = request.params(":quote");
            this.amount = Double.parseDouble(request.params(":amount"));
        }));
    }

    @Override
    public Currency getCurrency() {
        return this.currencies.stream().filter(currency -> currency.code().equals(this.baseCode)).findFirst().
                orElse(null);
    }

    @Override
    public Money getMoney() {
        return new Money(this.currencies.stream().filter(currency -> currency.code().equals(this.quoteCode)).
                findFirst().orElse(null), this.amount);
    }

}
