/**
 * Created by lenovo on 3/3/2015.
 */
public class Content implements Cloneable{
    @Override
    protected Content clone() throws CloneNotSupportedException {
        return (Content)super.clone();
    }
}
