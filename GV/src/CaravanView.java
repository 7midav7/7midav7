import java.awt.*;

/**
 * Created by lenovo on 2/11/2015.
 */
public class CaravanView implements AbstractView {
    CaravanController controller;
    ViewFrame frame;
    boolean isReady;

    public void setController(CaravanController controller) {
        this.controller = controller;
    }

    @Override
    public void markVertex(int vertex, int color) {
        DrawComponent dc = frame.getDrawComponent();
        MyRectangle rectangle = dc.getRectangle(vertex);
        rectangle.setColor(new Color(color));
        dc.repaint();
    }


    @Override
    public void valueVertex(int vertex, int value) {
        DrawComponent dc = frame.getDrawComponent();
        MyRectangle rectangle = dc.getRectangle(vertex);
        rectangle.setValue(Integer.toString(value));
        dc.repaint();
    }

    @Override
    public boolean isReady() {
        return isReady;
    }

    @Override
    public void endBuildGraph(AbstractModel model) {
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
                MyRectangle rect = new MyRectangle(j*width, i*height,
                        width, height, new Color(0xffffff), 0);
                rect.setValue(Integer.toString(model.getValueVertex(i * h + j)));
                dc.addRectangle(rect);
            }
        }

        isReady = true;
    }
}
