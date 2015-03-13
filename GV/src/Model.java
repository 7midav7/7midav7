import java.util.ArrayList;

/**
 * Created by lenovo on 2/11/2015.
 */
public class Model extends AbstractModel {
    @Override
    public void markVertex(int vertex, int color) {
        super.markVertex(vertex, color);
        try {
            Thread.sleep(100);
        } catch (InterruptedException exc){
            Thread.currentThread().interrupt();
        }
    }
}
