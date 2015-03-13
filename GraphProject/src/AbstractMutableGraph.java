/**
 * Created by lenovo on 3/10/2015.
 */
public interface AbstractMutableGraph extends AbstractGraph{

    public int addVertex(Content content);

    public void addEdge(Content content, int idFrom, int idTo);

    public void removeVertex(int id);

    public void removeEdge(int id);

    public void setVertexContent(int id, Content content);

    public void setEdgeContent(int id, Content content);


}
