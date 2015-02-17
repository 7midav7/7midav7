import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by lenovo on 2/13/2015.
 */
public class DrawComponent extends JComponent {
    ArrayList<MyRectangle> rectangleList = new ArrayList<MyRectangle>();

    public void addRectangle(MyRectangle shape){
        rectangleList.add(shape);
    }

    public MyRectangle getRectangle(int index){
        return rectangleList.get(index);
    }

    public MyRectangle rectangle(int index){
        if ( index >= rectangleList.size() ) {
            return null;
        }
        return rectangleList.get(index);
    }

    @Override
    protected void paintComponent(Graphics g) {
            Graphics2D gr2 = (Graphics2D) g;

            for (MyRectangle rectangle: rectangleList){
                rectangle.paintMe(gr2);
            }
        }

    @Override
    public Dimension getPreferredSize() {
        int DEFAULT_HEIGHT = 1000;
        int DEFAULT_WIDTH = 1000;

        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}
