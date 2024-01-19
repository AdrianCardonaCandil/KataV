package software.ulpgc.view.spark;

import software.ulpgc.model.Currency;
import software.ulpgc.model.FixerAPICurrencyLoader;
import software.ulpgc.model.Money;
import spark.Spark;

import java.util.Set;

public class SparkRequestAdapter implements Input{

    private final Set<Currency> currencySet = new FixerAPICurrencyLoader().loadCurrencies();
    private String quoteCode;
    private String baseCode;
    private double amount;

    public SparkRequestAdapter() {
        this.getRequestData();
    }

    private void getRequestData() {
        Spark.before("/exchange/:amount/:base/:quote", (request, response) -> {
            this.amount = Double.parseDouble(request.params(":amount"));
            this.baseCode = request.params(":base");
            this.quoteCode = request.params(":quote");
        });
    }

    @Override
    public Money getMoney() {
        return new Money(this.currencySet.stream().filter(currency -> currency.code().equals(this.baseCode)).findFirst().orElse(null), this.amount);
    }

    @Override
    public Currency getCurrency() {
        return this.currencySet.stream().filter(currency -> currency.code().equals(this.quoteCode)).findFirst().orElse(null);
    }
}
