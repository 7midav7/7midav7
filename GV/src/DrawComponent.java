import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

/**
 * Created by lenovo on 2/13/2015.
 */
public class DrawComponent extends JComponent {
    ArrayList<Shape> shapeList = new ArrayList<Shape>();
    ArrayList<Color> colorList = new ArrayList<Color>();

    public void addShape(Shape shape){
        shapeList.add(shape);
        colorList.add(new Color(0xffffff));
    }

    public Shape getShape(int index){
        if ( index >= shapeList.size() ) {
            return null;
        }
        return shapeList.get(index);
    }

    @Override
    protected void paintComponent(Graphics g) {
            Graphics2D gr2 = (Graphics2D) g;

            for (int i = 0; i < shapeList.size(); ++ i){
                gr2.setPaint( colorList.get(i) );
                gr2.fill(shapeList.get(i));
                gr2.draw(shapeList.get(i));
            }
        }

    @Override
    public Dimension getPreferredSize() {
        int DEFAULT_HEIGHT = 1000;
        int DEFAULT_WIDTH = 1000;

        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
}
