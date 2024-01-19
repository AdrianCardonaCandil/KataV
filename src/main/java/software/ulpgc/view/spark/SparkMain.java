package software.ulpgc.view.spark;

import spark.Spark;

public class SparkMain {

    public static void main(String[] args) {

        Spark.port(8080);

        /*
        For this to work:
        1. First, a command is executed when the URL is launched (Spark.before())
        2. Second, the data is received from the Spark Request (Spark.get())
        3. Finally, the Exchange Rate result is displayed on web app (Spark.after())
        * */

        RequestListener requestListener = new SparkRequestListener();
        requestListener.catchRequests(new SparkRequestAdapter(), new SparkResponseAdapter());
    }
}
