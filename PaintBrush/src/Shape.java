
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;


public abstract class Shape {
    
    int x1,y1, x2,y2;
    Color color;
    boolean solid;
    boolean normal;
    boolean dotted;
    
    //public static ArrayList<Shape> pencil = new ArrayList<Shape>();
     
    
    public abstract void draw(Graphics g);
    
    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isSolid() {
        return solid;
    }

    public void setSolid(boolean solid) {
        this.solid = solid;
    }
    
    
    
}
