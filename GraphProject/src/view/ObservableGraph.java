package view;

import graph.*;

import java.util.ArrayList;

/**
 * Created by lenovo on 3/10/2015.
 */
public class ObservableGraph implements ContentMutableGraph {

    private ArrayList<ViewObserver> listObserver = new ArrayList<ViewObserver>();

    public void addObserver(ViewObserver observer ){
        listObserver.add(observer);
    }

    private MutableGraph graph;

    public ObservableGraph(MutableGraph graph1){
        graph = graph1;
    }

    public void markVertex(int id, int color){
        for (ViewObserver observer : listObserver){
            observer.markVertex(id, color);
        }
    }

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

    @Override
    public void setVertexContent(int id, Content content) {
        graph.setVertexContent(id, content);
        for (ViewObserver observer : listObserver){
            observer.updateVertex(this.graph, id);
        }
    }

    @Override
    public void setEdgeContent(int id, Content content) {
        graph.setEdgeContent(id, content);
    }

}
