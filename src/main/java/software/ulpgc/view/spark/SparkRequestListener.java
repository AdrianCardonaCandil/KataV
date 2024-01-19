package software.ulpgc.view.spark;

import spark.Spark;

public class SparkRequestListener implements RequestListener{

    private final RequestHandler handler = new RequestHandler();

    @Override
    public void catchRequests(Input input, Output output) {
        Spark.get("/exchange/:amount/:quote/:base", (request, response) -> {
            handler.handleRequest(input, output);
            return "";
        });
    }
}
