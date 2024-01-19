package software.ulpgc.view.spark;

import software.ulpgc.model.Money;
import spark.Spark;

public class SparkResponseAdapter implements Output {

    @Override
    public void showExchangeRateResult(Money money) {
        Spark.after("/exchange/:amount/:quote/:base", (request, response) -> {
            System.out.println("hello");
            response.body(money.toString());
        });
    }

}