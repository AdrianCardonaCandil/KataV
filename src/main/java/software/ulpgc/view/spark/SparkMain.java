package software.ulpgc.view.spark;

import spark.Spark;

public class SparkMain {
    public static void main(String[] args) {
        Spark.port(8080);
        RequestListener requestListener = new SparkRequestListener();
        requestListener.catchToHandleRequest(new SparkRequestAdapter(), new SparkResponseAdapter());
    }
}
