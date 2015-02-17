import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by lenovo on 2/12/2015.
 */
public class ViewFrame extends JFrame {

    private DrawComponent drawComponent = new DrawComponent();

    ViewFrame() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        setSize(width / 2, height / 2);
        setLocation(width / 4, height / 4);

        add(drawComponent);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public DrawComponent getDrawComponent() {
        return drawComponent;
    }
}
