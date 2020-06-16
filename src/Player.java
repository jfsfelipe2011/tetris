import java.awt.*;

public class Player {
    public boolean right, left;
    public int x, y;
    public Block block;
    public boolean changeShape = false;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;

        block = new Block(x, y);
    }

    public void tick() {
        if (right && canGo(x + 4)) {
            x += 4;
        } else if (left && canGo(x - 4)) {
            x -= 4;
        }

        if (changeShape) {
            changeShape = false;

            block.shape++;

            if (block.shape == Shape.shapes.size()) {
                block.shape = 0;
            }
        }

        block.x = x;
        block.tick();
    }

    public boolean canGo(int nextx) {
        if (nextx < 0) {
            x = 0;
            return false;
        }

        int ww = Shape.shapes.get(block.shape).width;

        if (nextx + ww > 480) {
            x = 480 - ww;
            return false;
        }

        return true;
    }

    public void render(Graphics g) {
        block.render(g);
    }
}
