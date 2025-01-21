
import java.awt.Color;
import java.awt.Graphics;

public class Line extends Shape {

    public Line(int x1, int y1, int x2, int y2, Color c, boolean solidLine, boolean normalLine, boolean dottedLine) {

        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        this.color = c;
        this.solid = solidLine;
        this.normal = normalLine;
        this.dotted = dottedLine;
    }

    public Line(int x1, int y1, Color c,boolean solid, boolean normal, boolean dotted) {

        this.x1 = x1;
        this.y1 = y1;
        this.color = c;
         this.solid = solid;
        this.normal = normal;
        this.dotted = dotted;

    }

    public void drawDashedLine(Graphics g, int x1, int y1, int x2, int y2,
            double dashlength, double spacelength) {
        if ((x1 == x2) && (y1 == y2)) {
            g.drawLine(x1, y1, x2, y2);
            return;
        }
        double linelength = Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
        double yincrement = (y2 - y1) / (linelength / (dashlength + spacelength));
        double xincdashspace = (x2 - x1) / (linelength / (dashlength + spacelength));
        double yincdashspace = (y2 - y1) / (linelength / (dashlength + spacelength));
        double xincdash = (x2 - x1) / (linelength / (dashlength));
        double yincdash = (y2 - y1) / (linelength / (dashlength));
        int counter = 0;
        for (double i = 0; i < linelength - dashlength; i += dashlength + spacelength) {
            g.drawLine((int) (x1 + xincdashspace * counter),
                    (int) (y1 + yincdashspace * counter),
                    (int) (x1 + xincdashspace * counter + xincdash),
                    (int) (y1 + yincdashspace * counter + yincdash));
            counter++;
        }
        if ((dashlength + spacelength) * counter <= linelength) {
            g.drawLine((int) (x1 + xincdashspace * counter),
                    (int) (y1 + yincdashspace * counter),
                    x2, y2);
        }
    }

    public Line() {
    }

    @Override
    public void draw(Graphics g) {
        if (normal || solid) {
            g.setColor(color);
            g.drawLine(x1, y1, x2, y2);

        } else if (dotted) {
            g.setColor(color);
            drawDashedLine(g, x1, y1, x2, y2, 5, 5);
        }
    }

}
