package graph;

/**
 * Created by lenovo on 3/3/2015.
 */
public final class Edge {
    private Content content;
    private int id;
    private int fromVertex;
    private int toVertex;

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public int getId() {

        return id;
    }

    public Edge(Content content, int id,
                int fromVertex, int toVertex){
        this.content = content;
        this.id = id;
        this.fromVertex = fromVertex;
        this.toVertex = toVertex;
    }

    public int getFromVertex() {
        return fromVertex;
    }

    public int getToVertex() {
        return toVertex;
    }
}
