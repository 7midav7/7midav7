package graph;

/**
 * Created by lenovo on 3/3/2015.
 */
public class GraphLoadingException extends GraphException {
    public GraphLoadingException() {
        super();
    }

    public GraphLoadingException(String message) {
        super(message);
    }

    public GraphLoadingException(String message, Throwable cause) {
        super(message, cause);
    }

    public GraphLoadingException(Throwable cause) {
        super(cause);
    }
}
