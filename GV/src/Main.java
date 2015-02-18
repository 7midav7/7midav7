import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by lenovo on 2/11/2015.
 */
public class Main {
    public static void caravanProblem() {
        AbstractModel model = new Model();
        CaravanController controller = new CaravanController();
        CaravanView view = new CaravanView();

        model.setView(view);
        controller.setModel(model);
        view.setController(controller);

        try{
            controller.readData(new InputStreamReader( new FileInputStream("caravan.in")));
            controller.findMinLength();
            controller.findTrace();
        } catch ( IOException exc ){
            System.err.println(exc.getMessage());
        }
    }

    public static void castleProblem(){
        AbstractModel model = new Model();
        CastleController controller = new CastleController();
        CastleView view = new CastleView();

        model.setView(view);
        controller.setModel(model);
        view.setController(controller);

        try{
            controller.readData(new InputStreamReader( new FileInputStream("castle.in")));
            controller.findRooms();
        } catch ( IOException exc ){
            System.err.println(exc.getMessage());
        }
    }

    public static void main(String[] args) {
        castleProblem();
    }
}
