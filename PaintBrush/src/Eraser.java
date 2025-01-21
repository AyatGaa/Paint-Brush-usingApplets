
import java.awt.Color;
import java.awt.Graphics;

public class Eraser extends Shape {

    public Eraser(int x1, int y1) {

        this.x1 = x1;
        this.y1 = y1;//only start point
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillRect(x1, y1, 15, 15);

    }
}
