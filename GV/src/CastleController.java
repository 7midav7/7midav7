import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

/**
 * Created by lenovo on 2/17/2015.
 */
public class CastleController {
    private AbstractModel model;

    private int height = 0;
    private int width = 0;

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public void setModel(AbstractModel model) {
        this.model = model;
    }

    public void readData(InputStreamReader isr) throws IOException {
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

                if ( (i != 0) && ( (temp&2) == 0 )  ){
                     model.addEdge(num, num - this.height);
                }
                if ( ( j != 0) && ( ( (temp&1) == 0 ) ) ) {
                    model.addEdge(num, num - 1);
                }
            }
            if ( i + 1 != this.height ) {
                tokenizer = new StringTokenizer(reader.readLine());
            }
        }

        model.endBuildGraph();
    }

    private void dfs(int index, int color){
        if ( model.getMarkVertex(index) != 0) return;

        model.markVertex(index, color);

        for( Integer neighbors : model.getNeighbors(index)) {
            dfs(neighbors, color);
        }
    }

    public void findRooms(){
        for ( int i = 0; i < this.height * this.width; ++ i)
            if ( model.getMarkVertex(i) == 0 ) dfs(i, Marks.nextMark());
    }
}
