package software.ulpgc.view.spark;

import spark.Spark;

public class SparkRequestListener implements RequestListener{
    private final RequestHandler handler = new RequestHandler();
    @Override
    public void catchToHandleRequest(Input input, Output output) {
        Spark.get("/exchange/:amount/:base/:quote", (request, response) -> {
            this.handler.handleRequest(input, output);
            return "";
        });
    }
}
