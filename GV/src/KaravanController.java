import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * Created by lenovo on 2/11/2015.
 */
public class KaravanController {

   private AbstractModel model;
   int from;
   int to;

   KaravanController(AbstractModel model){
       this.model = model;
   }

   public void readData(InputStreamReader isr) throws IOException{
       BufferedReader reader = new BufferedReader( isr );
       StringTokenizer tokenizer = new StringTokenizer( reader.readLine() );

       int n = Integer.parseInt(tokenizer.nextToken());
       int m = Integer.parseInt(tokenizer.nextToken());

       tokenizer = new StringTokenizer( reader.readLine() );
       for ( int i = 0; i < n; ++ i ) {
           for (int j = 0; j < m; ++j){
               int temp = Integer.parseInt(tokenizer.nextToken());

               int num = model.addVertex();
               model.setValueVertex(num, temp);

               if ( i != 0 ){
                   if (model.getValueVertex(num - n) >= temp){
                       model.addArc(num - n, num);
                   }
                   if (model.getValueVertex(num - n) <= temp){
                       model.addArc(num, num - n);
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

       from = Integer.parseInt(tokenizer.nextToken());
       to = Integer.parseInt(tokenizer.nextToken());
   }

   public void findMinTrace(){
       ArrayDeque<Integer> deque = new ArrayDeque<Integer>();
       ArrayDeque<Integer> distance = new ArrayDeque<Integer>();

       deque.add(from);
       distance.add(0);

       while (deque.size() != 0){
           int cur = deque.pollFirst();
           int curDistance = distance.pollFirst();

           ArrayList<Integer> neighbors = model.getNeighbors(cur);

           for ( Integer vertex: neighbors){
               if (model.getMarkVertex(vertex) == 0){
                   model.markVertex(vertex, 100);

                   deque.add(vertex);
                   distance.add(curDistance + 1);
               }
               if ( vertex == to ){
                   model.markVertex(vertex, 200);
                   System.out.println(curDistance + 1);
               }
           }
       }
   }
}
