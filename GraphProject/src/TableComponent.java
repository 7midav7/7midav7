import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by lenovo on 2/13/2015.
 */
public class TableComponent extends JComponent {

    private static int spaceWidth = 50;
    private static int spaceHeight = 50;

    protected ArrayList<MyRectangle> rectangleList = new ArrayList<MyRectangle>();

    public TableComponent(AbstractGraph graph, Dimension table, Dimension frame) {

        int blockWidth = (frame.width - spaceWidth) / table.width;
        int blockHeight = (frame.height - spaceHeight) / table.height;

        for (int i = 0; i < table.height; ++ i){
            for (int j = 0; j < table.width; ++ j){
                Rectangle rect = new Rectangle(j * blockWidth, i * blockHeight,
                        blockWidth, blockHeight);
                MyRectangle rectangle = new MyRectangle(rect, new Color(0xffffff), 0);
                rectangleList.add(rectangle);
            }
        }

    }

    public MyRectangle getRectangle(int index){
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
