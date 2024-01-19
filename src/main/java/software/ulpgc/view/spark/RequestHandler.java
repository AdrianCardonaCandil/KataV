package software.ulpgc.view.spark;

import software.ulpgc.model.ExchangeRate;
import software.ulpgc.model.FixedAPIExchangeRateLoader;

import software.ulpgc.model.Money;

public class RequestHandler {

    public void handleRequest(Input input, Output output) {
        ExchangeRate exchangeRate = new FixedAPIExchangeRateLoader().loadExchangeRate(input.getMoney().currency(),
                input.getCurrency());
        output.showExchangeRateResult(new Money(input.getCurrency(), input.getMoney().amount() *
                exchangeRate.rate()));
    }
}
