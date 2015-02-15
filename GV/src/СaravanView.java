import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Created by lenovo on 2/11/2015.
 */
public class СaravanView implements AbstractView {
    СaravanController controller;
    ViewFrame frame = new ViewFrame();

    public void setController(СaravanController controller) {
        this.controller = controller;
    }

    @Override
    public void markVertex(int vertex, int color) {
         DrawComponent dc = frame.getDrawComponent();


         Rectangle2D rect = (Rectangle2D) dc.getShape(vertex);

    }

    @Override
    public void endBuildGraph(ArrayList<ArrayList<Integer>> list) {
        int h = controller.getHeight();
        int w = controller.getWidth();

        int spaceRight = 100;
        int spaceDown = 100;

        frame = new ViewFrame();
        int width = ( frame.getWidth() - spaceRight ) / w;
        int height = ( frame.getHeight() - spaceDown ) / h;

        DrawComponent dc = frame.getDrawComponent();

        for (int i = 0; i < h; ++ i){
            for (int j = 0; j < w; ++ j){
                Rectangle2D rect = new Rectangle2D.Double(i*width, j*height, width, height);
                dc.addShape(rect);
            }
        }
    }
}
