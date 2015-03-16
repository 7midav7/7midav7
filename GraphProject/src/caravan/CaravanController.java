package caravan;

import graph.*;
import view.ObservableGraph;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by lenovo on 3/10/2015.
 */
public class CaravanController {

    private int from;
    private int to;
    private ContentMutableGraph graph;

    private void init() throws GraphLoadingException {

        MutableGraph graph = new DefaultGraph();
        int height;
        int width;

        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream("caravan.in")); // bad style
            BufferedReader reader = new BufferedReader( isr );
            StringTokenizer tokenizer = new StringTokenizer( reader.readLine() );

            height = Integer.parseInt(tokenizer.nextToken());
            width = Integer.parseInt(tokenizer.nextToken());

            tokenizer = new StringTokenizer( reader.readLine() );
            for ( int i = 0; i < height; ++ i ) {
                for (int j = 0; j < width; ++j){

                    int temp = Integer.parseInt(tokenizer.nextToken());

                    Content content = new CaravanContent(temp, -1);
                    int num = graph.addVertex(content);

                    if ( i != 0 ){
                        if ( ( (CaravanContent)graph.getVertexContent(num - height)
                            ).getHeight() >= temp){
                            graph.addEdge(new Content(), num - height, num);
                        }
                        if ( ( (CaravanContent)graph.getVertexContent(num - height)
                            ).getHeight() <= temp){
                            graph.addEdge(new Content(), num, num - height);
                        }
                    }
                    if ( j != 0){
                        if (((CaravanContent)graph.getVertexContent(num - 1)
                            ).getHeight() >= temp){
                            graph.addEdge(new Content(), num - 1, num);
                        }
                        if (((CaravanContent)graph.getVertexContent(num - 1)
                            ).getHeight() <= temp){
                            graph.addEdge(new Content() ,num, num - 1);
                        }
                    }
                }
                tokenizer = new StringTokenizer(reader.readLine());
            }

            this.from = Integer.parseInt(tokenizer.nextToken());
            this.to = Integer.parseInt(tokenizer.nextToken());

        } catch (IOException e){
            throw new GraphLoadingException();
        }

        ObservableGraph viewableGraph = new ObservableGraph(graph);
        CaravanView caravanView = new CaravanView(viewableGraph, new Dimension(width, height));
        viewableGraph.addObserver(caravanView);
        this.graph = viewableGraph;

    }

    private void findMinTrace(){
        ArrayDeque<Integer> deque = new ArrayDeque<Integer>();

        deque.add(this.from);
        CaravanContent startContent = (CaravanContent)graph.getVertexContent(this.from);
        startContent.setCameFromId(-1);
        startContent.setLengthWay(0);
        graph.setVertexContent(this.from, startContent);

        main_break:
        while (!deque.isEmpty()){

            int curId = deque.pollFirst();
            Vertex curVertex = graph.getVertex(curId);
            CaravanContent curContent =
                    (CaravanContent)curVertex.getContent();
            int length = curContent.getLengthWay();
            ArrayList<Integer> neighbors = curVertex.getListEdge();

            for (Integer curEdge : neighbors){
                int idVertex = graph.getEdge(curEdge).getToVertex();
                CaravanContent content =
                        (CaravanContent)graph.getVertexContent(idVertex);
                if (content.getLengthWay() == -1){
                    content.setLengthWay(length + 1);
                    content.setCameFromId(curId);
                    graph.setVertexContent(idVertex, content);
                    deque.addLast(idVertex);
                }
                if (idVertex == this.to - 1){
                    break main_break;
                }
            }
        }
    }

    private void markMinTrace(){
        int curId = ((CaravanContent)graph.getVertexContent(this.to - 1))
                .getCameFromId();
        while (curId != -1){
            ((ObservableGraph)graph).markVertex(curId, 0xbb0000);
            curId = ((CaravanContent)graph.getVertexContent(curId))
                    .getCameFromId();
        }
    }

    public void doAlgorithm() throws GraphLoadingException {
        init();
        findMinTrace();
        markMinTrace();
    }

}
