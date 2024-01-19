package software.ulpgc.view.spark;

import software.ulpgc.model.Money;
import spark.Spark;

public class SparkResponseAdapter implements Output{

    @Override
    public void displayExchangeResult(Money money) {
        Spark.after("/exchange/:amount/:base/:quote", (request, response) -> {
            response.body(money.toString());
        });
    }

}
