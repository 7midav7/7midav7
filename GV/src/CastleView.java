import java.awt.*;

/**
 * Created by lenovo on 2/17/2015.
 */
public class CastleView implements AbstractView{
    ViewFrame frame;
    CastleController controller;
    private boolean isReady;

    @Override
    public void markVertex(int vertex, int color) {
        DrawComponent dc = frame.getDrawComponent();
        MyRectangle rectangle = dc.getRectangle(vertex);
        rectangle.setColor(new Color(color));
        dc.repaint();
    }

    @Override
    public boolean isReady() {
        return isReady;
    }

    @Override
    public void valueVertex(int vertex, int value) {
        DrawComponent dc = frame.getDrawComponent();
        MyRectangle rectangle = dc.getRectangle(vertex);
        rectangle.setValue(Integer.toString(value));
        dc.repaint();
    }

    public CastleController getController() {
        return controller;
    }

    public void setController(CastleController controller) {
        this.controller = controller;
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
                        width, height, new Color(0xffffff), 0xffffff);

                rect.changeBorders(model.getValueVertex(i * h + j));
                rect.setValue(Integer.toString(model.getValueVertex(i * h + j)));

                dc.addRectangle(rect);
            }
        }
    }


}
