import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Shape {
    public static Random random = new Random();
    public static ArrayList<Rectangle> shapes = new ArrayList<>();

    public Shape() {
        shapes.add(new Rectangle(0, 0, 100, 20));
        shapes.add(new Rectangle(0, 0, 20, 100));
        shapes.add(new Rectangle(0, 0, 50, 50));
    }
}
