
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Oval extends Shape {

    public Oval(int x1, int y1, int width, int height, Color c, boolean solid, boolean normal, boolean dotted) {

        this.x1 = x1;
        this.x2 = width;
        this.y1 = y1;
        this.y2 = height;
        this.color = c;
        this.solid = solid;
        this.normal = normal;
        this.dotted = dotted;

    }

    @Override
    public void draw(Graphics g) {
        if (normal) {

            g.setColor(color);
            g.drawOval(x1, y1, x2, y2);

        } else if (solid) {

            g.setColor(color);
            g.fillOval(x1, y1, x2, y2);

        } else if (dotted) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(color);

            float[] dash = {5f, 5f};
            g2d.setStroke(new BasicStroke(2, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, dash, 0));

            g2d.drawOval(x1, y1, x2, y2);
        }
    }

}
