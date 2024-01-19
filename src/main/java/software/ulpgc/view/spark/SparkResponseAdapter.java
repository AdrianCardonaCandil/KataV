package software.ulpgc.view.spark;

import software.ulpgc.model.Money;
import spark.Spark;

public class SparkResponseAdapter implements Output {

    @Override
    public void showExchangeRateResult(Money money) {
        Spark.after("/exchange/:amount/:quote/:base", (request, response) -> {
            response.body(money.toString());
        });
    }

}
