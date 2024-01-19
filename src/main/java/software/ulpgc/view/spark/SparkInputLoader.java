package software.ulpgc.view.spark;

import software.ulpgc.view.spark.SparkCurrencyDialog.OnCurrencyDialogUpdate;
import software.ulpgc.view.spark.SparkMoneyDialog.OnMoneyDialogUpdate;
import spark.Spark;

public class SparkInputLoader implements InputLoader {

    private OnCurrencyDialogUpdate currencyDialogUpdate;
    private OnMoneyDialogUpdate moneyDialogUpdate;

    public SparkInputLoader() {
        this.handleInput();
    }

    public void setCurrencyDialogUpdate(OnCurrencyDialogUpdate currencyDialogUpdate) {
        this.currencyDialogUpdate = currencyDialogUpdate;
    }

    public void setMoneyDialogUpdate(OnMoneyDialogUpdate moneyDialogUpdate) {
        this.moneyDialogUpdate = moneyDialogUpdate;
    }

    public void handleInput() {
        Spark.before("/exchange/:amount/:base/:quote", ((request, response) -> {
            SparkInputLoader.this.moneyDialogUpdate.updateMoneyDialog(request.params(":base"),
                    Double.parseDouble(request.params(":amount")));
            SparkInputLoader.this.currencyDialogUpdate.updateCurrencyDialog(request.params(":quote"));
        }));
    }
}
