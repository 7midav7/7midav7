package graph;

/**
 * Created by lenovo on 3/13/2015.
 */
public interface ContentMutableGraph extends Graph {

    public void setVertexContent(int id, Content content);

    public void setEdgeContent(int id, Content content);
}
