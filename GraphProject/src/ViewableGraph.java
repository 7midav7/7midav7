import java.util.ArrayList;

/**
 * Created by lenovo on 3/10/2015.
 */
public class ViewableGraph extends Graph {

    private ArrayList<ViewObserver> listObserver = new ArrayList<ViewObserver>();

    private void addObserver(ViewObserver observer ){
        listObserver.add(observer);
    }

}
