package software.ulpgc.view.spark;

import software.ulpgc.model.Currency;
import software.ulpgc.view.CurrencyDialog;

import java.util.Set;

public class SparkCurrencyDialog implements CurrencyDialog {

    public interface OnCurrencyDialogUpdate {
        void updateCurrencyDialog(String code);
    }

    private SparkInputLoader loader;
    public Set<Currency> currencies;
    private String code;

    public SparkCurrencyDialog(SparkInputLoader loader) {
        this.loader = loader;
        this.setAdapter();
    }

    private void setAdapter() {
        this.loader.setCurrencyDialogUpdate(code -> {
            SparkCurrencyDialog.this.code = code;
        });
    }

    public void setCode(String code) {
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
