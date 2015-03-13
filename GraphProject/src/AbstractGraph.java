/**
 * Created by lenovo on 3/10/2015.
 */
public interface AbstractGraph {
    public boolean hasVertex(int id);

    public boolean hasEdge(int id);

    public Vertex getVertex(int id);

    public Edge getEdge(int id);

    public Content getVertexContent(int id);

    public Content getEdgeContent(int id);
}
