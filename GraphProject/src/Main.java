import caravan.CaravanController;
import graph.GraphLoadingException;

/**
 * Created by lenovo on 3/3/2015.
 */
public class Main {
    public static void main(String[] args) throws GraphLoadingException {
        CaravanController controller = new CaravanController();
        controller.doAlgorithm();
    }
}
