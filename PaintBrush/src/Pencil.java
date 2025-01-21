
import java.awt.Color;
import java.awt.Graphics;

public class Pencil extends Shape {

    public Pencil(int x1, int y1, int x2, int y2, Color c,boolean solid, boolean normal, boolean dotted) {

        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.color = c;
      this.solid = solid;
        this.normal = normal;
        this.dotted = dotted;

    }

    public void draw(Graphics g) {

        if (normal || solid) {
            g.setColor(color);
            g.drawLine(x1, y1, x2, y2);

        } else if (dotted) {
            g.setColor(color);
            g.fillOval(x1, y1, 10, 10);
        }
    }
}
