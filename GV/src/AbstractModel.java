import java.util.ArrayList;

/**
 * Created by lenovo on 2/11/2015.
 */
public interface AbstractModel {

    public int addVertex();

    public void addEdge(int f, int d);

    public void addArc(int from, int to);

    public ArrayList<Integer> getNeighbors(int vertex);

    public void markVertex(int vertex, int color);

    public int getMarkVertex(int vertex);

    public void setValueVertex(int vertex, int value);

    public int getValueVertex(int vertex);

    public int size();

    public void endBuildGraph();
}
