import javax.swing.*;
import java.awt.*;

/**
 * Created by lenovo on 2/12/2015.
 */
public class ViewFrame extends JFrame {

    ViewFrame() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int width = screenSize.width;
        int height = screenSize.height;

        setSize(width / 2, height / 2);
        setLocation(width / 4, height / 4);

        setTitle("Caravan");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
