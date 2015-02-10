/**
 * Created by lenovo on 10.02.2015.
 */
public class TestMachine {

    private Stack stack;
    private double[] allTime = new double[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    private void doTest(double percentPush){
        for ( int i = 0; i < 11234567; ++i ){
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
        for ( int i = 1; i < 10; ++ i ){
            doTest( i / 10.0 );
        }
    }

    public double[] getTimeArray(){
        return allTime;
    }

}
