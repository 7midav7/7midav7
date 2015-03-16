package graph;

/**
 * Created by lenovo on 3/3/2015.
 */
public class GraphException extends Exception {
    public GraphException() {
        super();
    }

    public GraphException(String message) {
        super(message);
    }

    public GraphException(String message, Throwable cause) {
        super(message, cause);
    }

    public GraphException(Throwable cause) {
        super(cause);
    }
}
