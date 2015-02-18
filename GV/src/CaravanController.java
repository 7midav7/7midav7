import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by lenovo on 2/11/2015.
 */
public class CaravanController {

    private AbstractModel model;
    private int from;
    private int to;
    private int height = 0;
    private int width = 0;

    ArrayDeque<Integer> deque = new ArrayDeque<Integer>();
    ArrayDeque<Integer> distance = new ArrayDeque<Integer>();

    int[] lastSquare;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setModel(AbstractModel model) {
        this.model = model;
    }

    public void readData(InputStreamReader isr) throws IOException{
        BufferedReader reader = new BufferedReader( isr );
        StringTokenizer tokenizer = new StringTokenizer( reader.readLine() );

        this.height = Integer.parseInt(tokenizer.nextToken());
        this.width = Integer.parseInt(tokenizer.nextToken());

        tokenizer = new StringTokenizer( reader.readLine() );
        for ( int i = 0; i < this.height; ++ i ) {
            for (int j = 0; j < this.width; ++j){
                int temp = Integer.parseInt(tokenizer.nextToken());

                int num = model.addVertex();
                model.setValueVertex(num, temp);

                if ( i != 0 ){
                    if (model.getValueVertex(num - this.height) >= temp){
                        model.addArc(num - this.height, num);
                    }
                    if (model.getValueVertex(num - this.height) <= temp){
                        model.addArc(num, num - this.height);
                    }
                }
                if ( j != 0){
                    if (model.getValueVertex(num - 1) >= temp){
                        model.addArc(num - 1, num);
                    }
                    if (model.getValueVertex(num - 1) <= temp){
                        model.addArc(num, num - 1);
                    }
                }
            }
            tokenizer = new StringTokenizer(reader.readLine());
        }

        this.from = Integer.parseInt(tokenizer.nextToken());
        this.to = Integer.parseInt(tokenizer.nextToken());

        lastSquare = new int[this.height * this.width];
        for (int i = 0; i < lastSquare.length; ++ i ) {
            lastSquare[i] = -1;
        }

        model.endBuildGraph();
    }

    public void findMinLength(){

        deque.add(from);
        distance.add(0);
        model.markVertex(from, 0xaaffff);

        while (deque.size() != 0){

            int cur = deque.pollFirst();
            int curDistance = distance.pollFirst();

            ArrayList<Integer> neighbors = model.getNeighbors(cur);

            int curMark = Marks.nextMark(curDistance);
            for ( Integer vertex: neighbors){

                if (model.getMarkVertex(vertex) == 0){
                    model.markVertex(vertex, curMark);

                    deque.add(vertex);
                    lastSquare[vertex] = cur;
                    distance.add(curDistance + 1);
                }
                if ( vertex == to ){
                    model.markVertex(vertex, 0xaa0000);
                    System.out.println(curDistance + 1);
                    return;
                }
            }
        }
    }

    public void findTrace(){
        int cur = lastSquare[this.to];
        while ( cur != -1 ){
            model.markVertex(cur, 0xaa0000);
            cur = lastSquare[cur];
        }
    }
}
