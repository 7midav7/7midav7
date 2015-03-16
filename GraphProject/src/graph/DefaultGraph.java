package graph;

import java.util.ArrayList;

/**
 * Created by lenovo on 3/3/2015.
 */
public class DefaultGraph implements MutableGraph {

    protected ArrayList<Edge> listEdge = new ArrayList<Edge>();
    protected ArrayList<Vertex> listVertex = new ArrayList<Vertex>();

    public int addVertex(Content content){
        if ( content == null ){
            throw new IllegalArgumentException("graph.Content is null");
        }
        Vertex vertex = new Vertex(content, listVertex.size());
        listVertex.add(vertex);
        return listVertex.size() - 1;
    }

    public boolean hasVertex(int id){
        return !( id < 0 || id >= listVertex.size() ||
                listVertex.get(id) == null);
    }

    public boolean hasEdge(int id){
        return !( id < 0 || id >= listEdge.size() ||
                listEdge.get(id) == null);
    }

    public void addEdge(Content content, int idFrom, int idTo){
        if ( content == null ){
            throw new IllegalArgumentException("The content is null");
        }
        if ( !hasVertex(idTo) ){
            throw new IllegalArgumentException("The vertex doesn't exist in the graph");
        }
        if ( !hasVertex(idFrom) ){
            throw new IllegalArgumentException("The vertex doesn't exist in the graph");
        }
        Edge edge = new Edge(content, listEdge.size(),
                    idFrom, idTo);
        listVertex.get(idFrom).addEdge(edge);
        listVertex.get(idTo).addReverseEdge(edge);
        listEdge.add(edge);
    }

    public void removeVertex(int id){
        if ( !hasVertex(id) ){
            throw new IllegalArgumentException("The vertex doesn't exist in the graph");
        }
        listVertex.set(id, null);
    }

    public void removeEdge(int id){
        if (!hasEdge(id)){
            throw new IllegalArgumentException("The edge doesn't exist in the graph");
        }
        listEdge.set(id, null);
    }

    public Vertex getVertex(int id){
        if (!hasVertex(id)){
            throw new IllegalArgumentException("The vertex doesn't exist in the graph");
        }
        return listVertex.get(id);
    }

    public Edge getEdge(int id){
        if (!hasEdge(id)){
            throw new IllegalArgumentException("The edge doesn't exist in the graph");
        }
        return listEdge.get(id);
    }

    public void setVertexContent(int id, Content content){
        if (!hasVertex(id)){
            throw new IllegalArgumentException("The vertex doesn't exist in the graph");
        }
        if ( content == null ){
            throw new IllegalArgumentException("The content is null");
        }
        listVertex.get(id).setContent(content);
    }

    public Content getVertexContent(int id){
        if (!hasVertex(id)){
            throw new IllegalArgumentException("The vertex doesn't exist in the graph");
        }
        return listVertex.get(id).getContent();
    }

    public void setEdgeContent(int id, Content content){
        if (!hasEdge(id)){
            throw new IllegalArgumentException("The edge doesn't exist in the graph");
        }
        if ( content == null ){
            throw new IllegalArgumentException("The content is null");
        }
        listEdge.get(id).setContent(content);
    }

    public Content getEdgeContent(int id){
        if (!hasEdge(id)){
            throw new IllegalArgumentException("The edge doesn't exist in the graph");
        }
        return listEdge.get(id).getContent();
    }
}