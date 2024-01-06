package software.ulpgc.view.mock;

import software.ulpgc.model.Currency;
import software.ulpgc.model.Money;
import software.ulpgc.view.MoneyDialog;

import java.util.Scanner;
import java.util.Set;

public class MockMoneyDialog implements MoneyDialog {

    public final MockCurrencyDialog currencyDialog = new MockCurrencyDialog();

    @Override
    public void setCurrencies(Set<Currency> currencies) {
        this.currencyDialog.setCurrencies(currencies);
    }

    @Override
    public Money get() {
        double amount = new Scanner(System.in).nextDouble();
        return new Money(this.currencyDialog.get(), amount);
    }
}
