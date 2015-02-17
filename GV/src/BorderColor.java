import java.awt.*;

/**
 * Created by lenovo on 2/17/2015.
 */
public class BorderColor {
    private Color northBorder;
    private Color westBorder;
    private Color eastBorder;
    private Color southBorder;

    public BorderColor(Color northBorder, Color westBorder, Color eastBorder, Color southBorder) {
        this.northBorder = northBorder;
        this.westBorder = westBorder;
        this.eastBorder = eastBorder;
        this.southBorder = southBorder;
    }

    public BorderColor() {
        this.northBorder = new Color(0);
        this.westBorder = new Color(0);
        this.eastBorder = new Color(0);
        this.southBorder = new Color(0);
    }

    public Color getNorthBorder() {
        return northBorder;
    }

    public void setNorthBorder(Color northBorder) {
        this.northBorder = northBorder;
    }

    public Color getWestBorder() {
        return westBorder;
    }

    public void setWestBorder(Color westBorder) {
        this.westBorder = westBorder;
    }

    public Color getEastBorder() {
        return eastBorder;
    }

    public void setEastBorder(Color eastBorder) {
        this.eastBorder = eastBorder;
    }

    public Color getSouthBorder() {
        return southBorder;
    }

    public void setSouthBorder(Color southBorder) {
        this.southBorder = southBorder;
    }
}
