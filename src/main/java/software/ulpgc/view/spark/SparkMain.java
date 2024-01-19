package software.ulpgc.view.spark;

import software.ulpgc.controller.Command;
import software.ulpgc.controller.ExchangeCommand;
import software.ulpgc.model.Currency;
import software.ulpgc.model.FixedAPIExchangeRateLoader;
import software.ulpgc.model.FixerAPICurrencyLoader;
import spark.Spark;

import java.util.Set;

public class SparkMain {
    public static void main(String[] args) {

        Spark.port(8080);

        /*
        For this to work:
        1. Whenever the input comes from internet, the data on the different classes should be updates. (Spark.before())
        2. The command for the exchange rate should be also executed. (Spark.get())
        * */

        SparkInputLoader loader = new SparkInputLoader();
        Set<Currency> currencies = new FixerAPICurrencyLoader().loadCurrencies();
        SparkMoneyDialog moneyDialog = new SparkMoneyDialog(loader);
        SparkCurrencyDialog currencyDialog = new SparkCurrencyDialog(loader);
        moneyDialog.setCurrencies(currencies);
        currencyDialog.setCurrencies(currencies);

        Spark.get("/exchange/:amount/:base/:quote", ((request, response) -> {
            Command command = new ExchangeCommand(moneyDialog, currencyDialog, new FixedAPIExchangeRateLoader(), new SparkMoneyDisplay());
            command.execute();
            return "";
        }));
    }
}
