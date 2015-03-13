import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by lenovo on 2/25/2015.
 */
class Coordinates implements Comparable{
    int x;
    int y;

    Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Object o) {
        Coordinates second = (Coordinates) o;
        if (this.x < second.x) return -1;
        if (second.x < this.x) return 1;
        if (this.y < second.y) return -1;
        if (second.y < this.y) return 1;
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Coordinates that = (Coordinates) o;

        if (x != that.x) return false;
        if (y != that.y) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}

class CharacteristicWay{
    Coordinates delta;
    Coordinates finish;

    CharacteristicWay(Coordinates delta, Coordinates finish) {
        this.delta = delta;
        this.finish = finish;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CharacteristicWay that = (CharacteristicWay) o;

        if (!delta.equals(that.delta)) return false;
        if (!finish.equals(that.finish)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = delta.hashCode();
        result = 31 * result + finish.hashCode();
        return result;
    }
}

public class Main {

    private static CharacteristicWay calculateCharacteristicWay(Coordinates first,
            Coordinates second, int vSize, int hSize){

        int deltaX = second.x - first.x;
        int deltaY = second.y - first.y;
        Coordinates delta = new Coordinates(deltaX, deltaY);

        int v = vSize - second.y;
        int h = hSize - second.x;

        int temp;
        if ( deltaX == 0 ){
            temp = v / deltaY;
        } else {
            if (deltaY == 0) {
                temp = h / deltaX;
            } else {
                if ( Math.abs ( v / deltaY ) < Math.abs(h / deltaX ) ){
                    temp = v / deltaY;
                } else {
                    temp = h / deltaX;
                }
            }
        }
        Coordinates finish = new Coordinates(second.x + temp * deltaX,
                second.y + temp * deltaY);
        return new CharacteristicWay(delta, finish);

    }

    private static int getAnswer(HashMap<CharacteristicWay, Integer> hashMap){
        int tempAnswer = 2;

        for (Map.Entry<CharacteristicWay, Integer> entry : hashMap.entrySet()){

            Integer length = entry.getValue() + 1;
            CharacteristicWay cw = entry.getKey();

            int deltaX = cw.delta.x;
            int deltaY = cw.delta.y;
            int fX = cw.finish.x;
            int fY = cw.finish.y;

            if (  ( fX - deltaX * length < 1 || fY - deltaY * length < 1 )
                    && (tempAnswer < length)){
                tempAnswer = length;
            }
        }

        if (tempAnswer < 3){
            return 0;
        }
        return tempAnswer;
    }

    public static void main(String[] args) throws IOException{

        Scanner scanner = new Scanner(new File("input.txt"));

        int verticalSize = scanner.nextInt();
        int horizontalSize = scanner.nextInt();
        int numerousPoints = scanner.nextInt();

        Coordinates[] array = new Coordinates[numerousPoints];
        for (int i = 0; i < numerousPoints; ++ i){
            int y = scanner.nextInt();
            int x = scanner.nextInt();
            array[i] = new Coordinates(x, y);
        }

        HashMap<CharacteristicWay, Integer> hashMap =
                new HashMap<CharacteristicWay, Integer>();

        for (int i = 0; i < numerousPoints; ++ i ){
            for (int j = i + 1; j < numerousPoints; ++ j){
                Coordinates firstPoint;
                Coordinates secondPoint;

                if (array[i].compareTo(array[j]) == -1){
                    firstPoint = array[i];
                    secondPoint = array[j];
                } else {
                    firstPoint = array[j];
                    secondPoint = array[i];
                }

                CharacteristicWay cw = calculateCharacteristicWay(firstPoint,
                        secondPoint, verticalSize, horizontalSize);

                Integer oldValue = hashMap.get(cw);
                if ( oldValue != null ) {
                    hashMap.put(cw, oldValue + 1);
                } else {
                    hashMap.put(cw, 1);
                }
            }
        }

        PrintWriter pw = new PrintWriter( new FileWriter("output.txt"));
        pw.println(getAnswer(hashMap));

        pw.flush();
        pw.close();
    }
}
