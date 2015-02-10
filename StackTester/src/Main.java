/**
 * Created by lenovo on 10.02.2015.
 */
public class Main {
    public static void main(String[] args) {
        TestMachine machineVadim = new TestMachine(new VadimStack());
        TestMachine machineVladik = new TestMachine(new VadimStack()); // VladikStack
        TestMachine machineJeka = new TestMachine(new VadimStack()); // JekaStack

        for (int i = 0; i < 10; ++ i){
            machineJeka.doAllTests();
            machineVadim.doAllTests();
            machineVladik.doAllTests();
        }

        double[] arrayJeka = machineJeka.getTimeArray().clone();
        double[] arrayVadim = machineVadim.getTimeArray().clone();
        double[] arrayVladik = machineVladik.getTimeArray().clone();

        for ( int i = 1; i < 10; ++ i ){
            System.out.println(arrayJeka[i] + " " + arrayVadim[i] + " " + arrayVladik[i]);
        }
    }
}
