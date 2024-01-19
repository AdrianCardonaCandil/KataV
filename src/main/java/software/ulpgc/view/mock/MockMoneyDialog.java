package software.ulpgc.view.mock;

import software.ulpgc.model.Currency;
import software.ulpgc.model.Money;
import software.ulpgc.view.MoneyDialog;
import java.util.Set;

public class MockMoneyDialog implements MoneyDialog {

    private double amount;
    public MockCurrencyDialog currencyDialog;

    public MockMoneyDialog(String code, double amount) {
        this.amount = amount;
        this.currencyDialog = new MockCurrencyDialog(code);
    }

    @Override
    public void setCurrencies(Set<Currency> currencies) {
        this.currencyDialog.setCurrencies(currencies);
    }

    @Override
    public Money get() {
        return new Money(this.currencyDialog.get(), this.amount);
    }

}
