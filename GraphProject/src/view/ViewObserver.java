package view;

import graph.Graph;

/**
 * Created by lenovo on 3/10/2015.
 */
public interface ViewObserver {
    public void updateVertex(Graph graph, int id);
    public void markVertex(int id, int color);
}
