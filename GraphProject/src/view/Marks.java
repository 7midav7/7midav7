package view;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by lenovo on 2/17/2015.
 */
public class Marks {
    private static int prevR;
    private static int prevG;
    private static int prevB;

    private static int curR;
    private static int curG;
    private static int curB;

    private static HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();

    private static void generateMark(){
        Random random = new Random();
        curR = random.nextInt(200);
        curG = random.nextInt(200);
        curB = random.nextInt(200);
    }

    private static int nextMark(){
        while (true){
            generateMark();
            if ( curB < 80 || Math.abs(curB - prevB) < 50 ) continue;
            if ( curR < 80 || Math.abs(curR - prevR) < 50 ) continue;
            if ( curG < 80 || Math.abs(curG - prevG) < 50 ) continue;
            break;
        }
        prevB = curB;
        prevG = curG;
        prevR = curR;

        return ( curB + curG * 16 + curR * 256 );
    }

    public static int nextMark(int x){
        if ( !hashMap.containsKey(x) ) {
            hashMap.put(x, nextMark());
        }
        return hashMap.get(x);
    }
}
