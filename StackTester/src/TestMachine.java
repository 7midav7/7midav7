/**
 * Created by lenovo on 10.02.2015.
 */
public class TestMachine {

    private Stack stack;
    private double[] allTime = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    public static final int NUM_TESTS = 11234567;
    public static final double SERIES_TEST = 10.0;

    private void doTest(double percentPush){
        for ( int i = 0; i < NUM_TESTS; ++i ){
            if ( Math.random() < percentPush ) {
                stack.push(Integer.toString(i));
            } else {
                if ( !stack.isEmpty() ){
                    stack.pop();
                }
            }
        }
    }

    TestMachine( Stack stack ){
        this.stack = stack;
    }

    public void doAllTests(){
        for ( int i = 1; i < SERIES_TEST; ++ i ){
            double startTime = System.nanoTime();
            doTest( i / SERIES_TEST  );
            allTime[i] += System.nanoTime() - startTime;
        }
    }

    public double[] getTimeArray(){
        return allTime;
    }

}
