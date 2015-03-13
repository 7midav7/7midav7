/**
 * Created by lenovo on 3/10/2015.
 */
public class CaravanContent extends Content {

    private int height;
    private int cameFromId = -1;
    private int lengthWay = -1;

    @Override
    protected CaravanContent clone() throws CloneNotSupportedException {
        CaravanContent obj = (CaravanContent)super.clone();
        obj.height = height;
        obj.lengthWay = lengthWay;

        return obj;
    }

    public CaravanContent(int height, int lengthWay) {
        this.height = height;
        this.lengthWay = lengthWay;
    }

    public int getHeight() {
        return height;
    }

    public int getLengthWay() {
        return lengthWay;
    }

    public int getCameFromId() {
        return cameFromId;
    }

    public void setCameFromId(int cameFromId) {
        this.cameFromId = cameFromId;
    }

    public void setLengthWay(int lengthWay) {
        this.lengthWay = lengthWay;
    }
}
