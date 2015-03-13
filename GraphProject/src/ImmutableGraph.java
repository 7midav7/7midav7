/**
 * Created by lenovo on 3/3/2015.
 */
public class ImmutableGraph implements AbstractGraph{

    private AbstractGraph graph;

    @Override
    public boolean hasVertex(int id) {
        return graph.hasVertex(id);
    }

    @Override
    public boolean hasEdge(int id) {
        return graph.hasEdge(id);
    }

    @Override
    public Vertex getVertex(int id) {
        return graph.getVertex(id);
    }

    @Override
    public Edge getEdge(int id) {
        return graph.getEdge(id);
    }

    @Override
    public Content getVertexContent(int id) {
        return graph.getVertexContent(id);
    }

    @Override
    public Content getEdgeContent(int id) {
        return graph.getEdgeContent(id);
    }

    public static class Builder {

        public ImmutableGraph build(AbstractGraph graph){
            return new ImmutableGraph(graph);
        }

    }

    private ImmutableGraph(AbstractGraph graph){
        this.graph = graph;
    }

}
