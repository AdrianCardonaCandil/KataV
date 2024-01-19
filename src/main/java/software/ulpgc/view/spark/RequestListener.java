package software.ulpgc.view.spark;

public interface RequestListener {
    public abstract void catchToHandleRequest(Input input, Output output);
}
