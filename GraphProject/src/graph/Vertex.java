package graph;

import java.util.ArrayList;

/**
 * Created by lenovo on 3/3/2015.
 */
public final class Vertex {
    private Content content;
    private int id;
    private ArrayList<Integer> listEdge = new ArrayList<Integer>();
    private ArrayList<Integer> listReverseEdge = new ArrayList<Integer>();

    public Vertex(Content content, int id){
        this.content = content;
        this.id = id;
    }

    public void addEdge(Edge edge){
        listEdge.add(edge.getId());
    }

    public void addReverseEdge(Edge edge){
        listReverseEdge.add(edge.getId());
    }

    public void setContent(Content content){
        this.content = content;
    }

    public Content getContent(){
        return content;
    }

    public ArrayList<Integer> getListReverseEdge() {
        return listReverseEdge;
    }

    public ArrayList<Integer> getListEdge() {

        return listEdge;
    }

    public int getId() {
        return id;
    }
}
