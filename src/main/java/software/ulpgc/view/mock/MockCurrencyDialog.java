package software.ulpgc.view.mock;

import software.ulpgc.model.Currency;
import software.ulpgc.view.CurrencyDialog;
import java.util.*;

public class MockCurrencyDialog implements CurrencyDialog {

    public Set<Currency> currencies = new HashSet<>();
    @Override
    public void setCurrencies(Set<Currency> currencies) {
        this.currencies = currencies;
    }

    @Override
    public Currency get() {
        String code = new Scanner(System.in).next();
        return this.currencies.stream().filter(currency -> currency.code().equals(code)).
                findFirst().orElse(null);
    }
}
