import javax.swing.*;
import java.awt.*;

/**
 * Created by lenovo on 3/10/2015.
 */
public class CaravanView extends JFrame {

    CaravanView(AbstractGraph graph, Dimension table) {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        setSize(width / 2, height / 2);
        setLocation(width / 4, height / 4);

        TableComponent drawComponent = new CaravanTableComponent(graph, table,
                new Dimension(width / 2, height / 2));
        add(drawComponent);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
