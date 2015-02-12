import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by lenovo on 2/11/2015.
 */
public class Main {
    public static void main(String[] args) {
        AbstractModel model = new Model();
        СaravanController controller = new СaravanController();
        AbstractView view = new СaravanView();

        model.setView(view);
        controller.setModel(model);
        ((СaravanView) view).setController(controller);

        try{
            controller.readData(new InputStreamReader( new FileInputStream("input.txt")));
            controller.findMinTrace();
        } catch ( IOException exc ){
            System.err.println(exc.getMessage());
        }
    }
}
