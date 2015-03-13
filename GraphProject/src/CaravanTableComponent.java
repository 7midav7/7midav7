import java.awt.*;

/**
 * Created by lenovo on 3/10/2015.
 */
public class CaravanTableComponent extends TableComponent {

    public CaravanTableComponent(AbstractGraph graph, Dimension table, Dimension frame) {
        super(graph, table, frame);

        int curId = 0;
        while( graph.hasVertex(curId) ){
            CaravanContent content = (CaravanContent)graph.getVertexContent(curId);
            rectangleList.get(curId).setValue(
                    Integer.toString(content.getHeight()));
            ++ curId;
        }

    }

}
