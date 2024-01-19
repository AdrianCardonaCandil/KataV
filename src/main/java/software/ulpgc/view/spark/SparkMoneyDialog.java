package software.ulpgc.view.spark;

import software.ulpgc.model.Currency;
import software.ulpgc.model.Money;
import software.ulpgc.view.MoneyDialog;

import java.util.Set;

public class SparkMoneyDialog implements MoneyDialog {

    public interface OnMoneyDialogUpdate {
        void updateMoneyDialog(String code, double amount);
    }

    private SparkInputLoader inputLoader;
    public SparkCurrencyDialog currencyDialog;
    private double amount;


    public SparkMoneyDialog(SparkInputLoader inputLoader) {
        this.inputLoader = inputLoader;
        this.currencyDialog = new SparkCurrencyDialog(inputLoader);
        this.setMoneyDialogAdapter();
    }

    private void setMoneyDialogAdapter() {
        this.inputLoader.setMoneyDialogUpdate(((code, amount) -> {
            SparkMoneyDialog.this.amount = amount;
            SparkMoneyDialog.this.currencyDialog.setCode(code);
        }));
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
