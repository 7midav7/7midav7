import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by lenovo on 2/11/2015.
 */
public class Main {
    public static void main(String[] args) {
        AbstractModel model = new Model(new KaravanView());
        KaravanController controller = new KaravanController( model );

        try{
            controller.readData(new InputStreamReader( new FileInputStream("input.txt")));
            controller.findMinTrace();
        } catch ( IOException exc ){
            System.err.println(exc.getMessage());
        }
    }
}
