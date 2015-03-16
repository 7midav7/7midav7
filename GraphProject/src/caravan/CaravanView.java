package caravan;

import graph.Graph;
import view.Marks;
import view.TableComponent;
import view.ViewObserver;

import javax.swing.*;
import java.awt.*;

/**
 * Created by lenovo on 3/10/2015.
 */
public class CaravanView extends JFrame implements ViewObserver{

    TableComponent drawComponent;

    CaravanView(Graph graph, Dimension table) {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        setSize(width / 2, height / 2);
        setLocation(width / 4, height / 4);

        drawComponent = new CaravanTableComponent(graph, table,
                new Dimension(width / 2, height / 2));
        add(drawComponent);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void markVertex(int id, int color) {
        drawComponent.changeColorRectangle(id, color );
        try{
            Thread.sleep(100);
        } catch (InterruptedException exc){

        }
    }

    @Override
    public void updateVertex(Graph graph, int id) {
        drawComponent.changeColorRectangle(id, Marks.nextMark(
                ((CaravanContent) graph.getVertexContent(id)).getLengthWay()) );
        try{
            Thread.sleep(100);
        } catch (InterruptedException exc){

        }
    }
}
