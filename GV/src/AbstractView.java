import java.util.ArrayList;

/**
 * Created by lenovo on 2/11/2015.
 */
public interface AbstractView {
    public void markVertex(int vertex, int color);
    public void valueVertex(int vertex, int value);
    public void endBuildGraph( AbstractModel list );
    public boolean isReady();
}
