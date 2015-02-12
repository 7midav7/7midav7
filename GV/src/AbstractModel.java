import java.util.ArrayList;

/**
 * Created by lenovo on 2/11/2015.
 */
public abstract class AbstractModel {
    private AbstractView view;
    ArrayList< ArrayList<Integer> > list = new ArrayList< ArrayList<Integer> >();
    ArrayList< Integer > values = new ArrayList<Integer>();
    ArrayList< Integer > marks = new ArrayList<Integer>();

    public void setView(AbstractView view) {
        this.view = view;
    }

    public int addVertex() {
        list.add(new ArrayList<Integer>());
        values.add(0);
        marks.add(0);
        return list.size() - 1;
    }

    public void addEdge(int f, int s) {
        addArc(f, s);
        addArc(s, f);
    }

    public void addArc(int from, int to) {
        list.get(from).add(to);
    }

    public void endBuildGraph() {
        view.endBuildGraph(list);
    }

    public ArrayList<Integer> getNeighbors(int vertex) {
        return list.get(vertex);
    }

    public void markVertex(int vertex, int color) {
        marks.set(vertex, color);
    }

    public int size() {
        return list.size();
    }

    public int getMarkVertex(int vertex) {
        return marks.get(vertex);
    }

    public void setValueVertex(int vertex, int value) {
        values.set(vertex, value);
    }

    public int getValueVertex(int vertex) {
        return values.get(vertex);
    }

}
