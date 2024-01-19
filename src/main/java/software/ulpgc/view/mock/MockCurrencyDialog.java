package software.ulpgc.view.mock;

import software.ulpgc.model.Currency;
import software.ulpgc.view.CurrencyDialog;
import java.util.*;

public class MockCurrencyDialog implements CurrencyDialog {

    private String code;
    public Set<Currency> currencies;

    public MockCurrencyDialog(String code) {
        this.code = code;
    }

    @Override
    public void setCurrencies(Set<Currency> currencies) {
        this.currencies = currencies;
    }

    @Override
    public Currency get() {
        return this.currencies.stream().filter(currency -> currency.code().equals(this.code)).
                findFirst().orElse(null);
    }
}
