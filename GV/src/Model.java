import java.util.ArrayList;

/**
 * Created by lenovo on 2/11/2015.
 */
public class Model implements AbstractModel {
    private AbstractView view;
    ArrayList< ArrayList<Integer> > list = new ArrayList< ArrayList<Integer> >();
    ArrayList< Integer > values = new ArrayList<Integer>();
    ArrayList< Integer > marks = new ArrayList<Integer>();

    public Model(AbstractView view) {
        this.view = view;
    }

    @Override
    public int addVertex() {
        list.add(new ArrayList<Integer>());
        values.add(0);
        marks.add(0);
        return list.size() - 1;
    }

    @Override
    public void addEdge(int f, int s) {
        addArc(f, s);
        addArc(s, f);
    }

    @Override
    public void addArc(int from, int to) {
        list.get(from).add(to);
    }

    @Override
    public void endBuildGraph() {
        
    }

    @Override
    public ArrayList<Integer> getNeighbors(int vertex) {
        return list.get(vertex);
    }

    @Override
    public void markVertex(int vertex, int color) {
        marks.set(vertex, color);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public int getMarkVertex(int vertex) {
        return marks.get(vertex);
    }

    @Override
    public void setValueVertex(int vertex, int value) {
        values.set(vertex, value);
    }

    @Override
    public int getValueVertex(int vertex) {
        return values.get(vertex);
    }


}
