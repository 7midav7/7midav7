import java.util.ArrayList;

/**
 * Created by lenovo on 2/11/2015.
 */
public class СaravanView implements AbstractView {
    СaravanController controller;

    public void setController(СaravanController controller) {
        this.controller = controller;
    }

    @Override
    public void markVertex(int vertex, int color) {

    }

    @Override
    public void endBuildGraph(ArrayList<ArrayList<Integer>> list) {
        int h = controller.getHeight();
        int w = controller.getWidth();

        ViewFrame frame = new ViewFrame();

    }
}
