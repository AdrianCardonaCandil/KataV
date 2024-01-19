package software.ulpgc.view.spark;

import spark.Spark;

public class SparkRequestListener implements RequestListener{
    private final RequestHandler requestHandler = new RequestHandler();
    @Override
    public void catchToHandleRequest(Input input, Output output) {
        Spark.get( "/exchange/:amount/:base/:quote", (request, response) -> {
            requestHandler.HandleRequest(input, output);
            return "";
        });
    }
}
