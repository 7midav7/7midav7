package view;

import java.awt.*;

/**
 * Created by lenovo on 2/17/2015.
 */
public class BorderColor {

    public enum Type {WEST, NORTH, EAST, SOUTH};

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

    public BorderColor(int color) {
        this.northBorder = new Color(color);
        this.westBorder = new Color(color);
        this.eastBorder = new Color(color);
        this.southBorder = new Color(color);
    }

    public Color getColor(Type type ){
        switch (type){
            case WEST: return westBorder;
            case NORTH: return northBorder;
            case EAST: return eastBorder;
            case SOUTH: return southBorder;
            default: return null;
        }
    }

    public void setColor(Type type, int value ){
        Color color = new Color(value);
        switch (type){
            case WEST: westBorder = color; break;
            case NORTH: northBorder = color; break;
            case EAST: eastBorder = color; break;
            case SOUTH: southBorder = color; break;
        }
    }
}
