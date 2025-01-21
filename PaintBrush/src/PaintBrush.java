
import java.applet.Applet;
import java.awt.Button;
import java.awt.Graphics;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Font;
import java.awt.Label;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class PaintBrush extends Applet {

    boolean solidShape = false;
    boolean normalShape = true;
    boolean dottedShape = false;
    boolean cleard = false;

    int shapeType = 1;

    int currX1, currY1, currX2, currY2;
    ArrayList<Shape> shapes;
    Color c;
    Font f;
    Point clickPoint;

    @Override
    public void init() {
        setSize(1800, 800);
        f = new Font("defalut", Font.BOLD, 20);
        setFont(f);
        solidShape = false;
        normalShape = true;
        dottedShape = false;
        //function Button
        Button clear_btn = new Button(" Clear ");
        Button eraser_btn = new Button(" Eraser ");

        //color button
        Button red_btn = new Button(" Red ");
        red_btn.setBackground(Color.RED);
        Button green_btn = new Button(" Green ");
        green_btn.setBackground(Color.GREEN);
        Button blue_btn = new Button(" Blue ");
        blue_btn.setBackground(Color.BLUE);

        Button black_btn = new Button(" Black ");
        black_btn.setForeground(Color.WHITE);
        black_btn.setBackground(Color.BLACK);

        //shapes button
        Button line_btn = new Button(" Line ");
        Button rectangle_btn = new Button(" Rectangle ");
        Button oval_btn = new Button(" Oval ");
        Button pencil_btn = new Button(" Pencile ");

        Checkbox solid_cb = new Checkbox();
        Checkbox dotted_cb = new Checkbox();

        add(new Label("  Functions : "));
        add(clear_btn);
        add(eraser_btn);

        add(new Label("  Shapes : "));
        add(line_btn);
        add(rectangle_btn);
        add(oval_btn);
        add(pencil_btn);

        add(new Label("  Colors : "));
        add(red_btn);
        add(green_btn);
        add(blue_btn);
        add(black_btn);
        add(new Label("  Dotted : "));
        add(dotted_cb);
        add(new Label("  Solid :"));
        add(solid_cb);

        shapes = new ArrayList<>();

        //Handle Colors
        red_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                c = Color.RED;
            }
        });
        blue_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                c = Color.BLUE;
            }
        });
        green_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                c = Color.GREEN;
            }
        });
        black_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                c = Color.BLACK;
            }
        });

        //Shapes Buttons
        line_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                shapeType = 1;
            }
        });
        rectangle_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                shapeType = 2;
            }
        });
        oval_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                shapeType = 3;
            }
        });
        eraser_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                shapeType = 4;
            }
        });
        pencil_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                shapeType = 5;
            }
        });

        clear_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                shapes.clear();
                cleard = true;
                repaint();
            }
        });

        dotted_cb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                if (ie.getStateChange() == ItemEvent.SELECTED) {
                    dottedShape = true;
                    solidShape = false;
                    normalShape = false;
                } else {

                    normalShape = true;
                    dottedShape = false;
                }
            }
        });
        solid_cb.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent ie) {
                if (ie.getStateChange() == ItemEvent.SELECTED) {
                    solidShape = true;
                    normalShape = false;
                    dottedShape = false;
                } else {

                    normalShape = true;
                    solidShape = false;
                }

            }
        });

        //draw Shapes
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                currX1 = e.getX();
                currY1 = e.getY();
                clickPoint = new Point(e.getPoint());
                if (shapeType == 4) {//Eraser
                    shapes.add(new Eraser(currX1, currY1));
                }
            }

            public void mouseReleased(MouseEvent e) { /// end pioit of line
                currX2 = e.getX();
                currY2 = e.getY();

                if (shapeType == 1) {//line
                    shapes.add(new Line(currX1, currY1, currX2, currY2, c, solidShape, normalShape, dottedShape));

                } else if (shapeType == 2) { // rectangel
                    int minX = Math.min(e.getX(), clickPoint.x);
                    int minY = Math.min(e.getY(), clickPoint.y);
                    int maxX = Math.max(e.getX(), clickPoint.x);
                    int maxY = Math.max(e.getY(), clickPoint.y);
                    shapes.add(new Rectangle(minX, minY, (maxX - minX), (maxY - minY), c, solidShape, normalShape, dottedShape));

                } else if (shapeType == 3) { // ovals
                    int minX = Math.min(e.getX(), clickPoint.x);
                    int minY = Math.min(e.getY(), clickPoint.y);
                    int maxX = Math.max(e.getX(), clickPoint.x);
                    int maxY = Math.max(e.getY(), clickPoint.y);
                    shapes.add(new Oval(minX, minY, (maxX - minX), (maxY - minY), c, solidShape, normalShape, dottedShape));
                }
                repaint();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                currX2 = e.getX();
                currY2 = e.getY();
                if (shapeType == 4) {//Eraser
                    shapes.add(new Eraser(currX2, currY2));
                } else if (shapeType == 5) {//pencil

                    shapes.add(new Pencil(currX1, currY1, currX2, currY2, c, solidShape, normalShape, dottedShape));
                    currX1 = currX2;
                    currY1 = currY2;
                }
                repaint();
            }
        });

    }

    @Override
    public void paint(Graphics grphcs) {
        super.paint(grphcs);
        for (Shape l : shapes) {
            if (cleard == true) {
                grphcs.setColor(Color.WHITE);   
                grphcs.fillRect(0, 0, getWidth(), getHeight());
                cleard = false;
            }
            l.draw(grphcs);
        }

        //repaet drawing until release the mouse
        if (currX1 != currX2 || currY1 != currY2) { //not end point
            if (shapeType == 1) {
                new Line(currX1, currY1, currX2, currY2, c, solidShape, normalShape, dottedShape).draw(grphcs);
            } else if (shapeType == 2) {
                int minX = Math.min(currX1, currX2);
                int minY = Math.min(currY1, currY2);
                int width = Math.abs(currX2 - currX1);
                int height = Math.abs(currY2 - currY1);

                new Rectangle(minX, minY, width, height, c, solidShape, normalShape, dottedShape).draw(grphcs);
            } else if (shapeType == 3) {
                int minX = Math.min(currX1, currX2);
                int minY = Math.min(currY1, currY2);
                int width = Math.abs(currX2 - currX1);
                int height = Math.abs(currY2 - currY1);

                new Oval(minX, minY, width, height, c, solidShape, normalShape, dottedShape).draw(grphcs);
            } else if (shapeType == 4) {

                new Eraser(currX1, currY1).draw(grphcs);

            } else if (shapeType == 5) {
                new Pencil(currX1, currY1, currX2, currY2, c, solidShape, normalShape, dottedShape).draw(grphcs);
            }
        }
    }

}
