package view;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by lenovo on 2/16/2015.
 */
public class MyRectangle extends Rectangle2D.Double {
    private Color color;
    private String value;

    private BorderColor borderColor;

    public BorderColor getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(BorderColor borderColor) {
        this.borderColor = borderColor;
    }

    public MyRectangle(Rectangle rect, Color color, int borderColor) {
        super(rect.x, rect.y, rect.width, rect.height);
        this.color = color;
        this.borderColor = new BorderColor(borderColor);
        this.value = "";
    }

    public void paintMe(Graphics2D gr2){
        int x = (int)super.x;
        int y = (int)super.y;
        int w = (int)super.width;
        int h = (int)super.height;

        gr2.setPaint(color);
        gr2.fill(this);

        gr2.setStroke(new BasicStroke(2));

        gr2.setPaint(borderColor.getColor(BorderColor.Type.NORTH));
        gr2.drawLine(x, y, x + w, y);
        gr2.setPaint(borderColor.getColor(BorderColor.Type.WEST));
        gr2.drawLine(x, y, x, y + h);
        gr2.setPaint(borderColor.getColor(BorderColor.Type.EAST));
        gr2.drawLine(x + w, y, x + w, y + h);
        gr2.setPaint(borderColor.getColor(BorderColor.Type.SOUTH));
        gr2.drawLine(x, y + h, x + w, y + h);

        gr2.setStroke(new BasicStroke(2));

        gr2.setPaint(new Color(0));
        gr2.drawString( value, x + 20, y + 20);
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
