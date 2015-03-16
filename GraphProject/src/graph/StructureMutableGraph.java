package graph;

/**
 * Created by lenovo on 3/10/2015.
 */
public interface StructureMutableGraph extends Graph {

    public int addVertex(Content content);

    public void addEdge(Content content, int idFrom, int idTo);

    public void removeVertex(int id);

    public void removeEdge(int id);

}
