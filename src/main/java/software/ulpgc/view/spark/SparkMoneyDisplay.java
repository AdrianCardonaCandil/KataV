package software.ulpgc.view.spark;

import software.ulpgc.model.Money;
import software.ulpgc.view.MoneyDisplay;
import spark.Spark;

public class SparkMoneyDisplay implements MoneyDisplay {

    @Override
    public void display(Money money) {
        Spark.after("/exchange/:amount/:base/:quote", ((request, response) -> {
            response.body(money.toString());
        }));
    }
}
